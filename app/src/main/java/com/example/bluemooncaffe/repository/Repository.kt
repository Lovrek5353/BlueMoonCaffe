package com.example.bluemooncaffe.repository

import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.data.OrderManagement
import com.example.bluemooncaffe.data.Product
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

interface Repository {
    fun getAllProducts(): SharedFlow<List<Product>>
    fun getAllOrders(): SharedFlow<List<Order>>

    fun getLatestOrderId(): SharedFlow<Int>

    fun getJuices(): SharedFlow<List<Product>>

    fun addOrder(order: Order)

    fun assignToMe(id: Int)
    fun changeToProcessing(id: Int)
    fun changeToDelivered(id: Int)
    fun changeToPaid(id: Int)
    fun changeToCompleted(id: Int)

    fun fetchOrder(): SharedFlow<Order>
    fun addDrink(drink: Product)
    fun removeDrink(drink: Product)
    fun resetOrder()
    fun setTableNumber(tableNumber: Int)
}
internal class RepositoryImpl(
    private val orderManagement: OrderManagement
): Repository{
    private val flowScope = CoroutineScope(Dispatchers.Default)

    private val db = Firebase.firestore
    val storage= Firebase.storage.reference

    val allDrinksRef=db.collection("drinks")
    val allOrdersRef=db.collection("orders")

    val juicesRef=db.collection("products").whereEqualTo("categoryId",100)

    val allDrinksPublisher = MutableSharedFlow<List<Product>>()
    val allOrdersPublisher= MutableSharedFlow<List<Order>>()
    val orderLocalPublisher= MutableSharedFlow<Order>()
    val ordersIdPublisher= MutableSharedFlow<Int>()

    val juicesPublisher = MutableSharedFlow<List<Product>> ()

    private val allDrinksInitialFlow= callbackFlow<List<Product>> {
        val drinkList= mutableListOf<Product>()
        val registration= allDrinksRef.addSnapshotListener {result, exception ->
            if(exception!=null){
                close(exception)
            }
            else{
                for(document in result!!){
                    val product=document.toObject<Product>()
                    drinkList.add(product)
                }
                trySend(drinkList).isSuccess
            }
        }
        awaitClose {
            registration.remove()
        }
    }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    private val allOrdersInitialFlow= callbackFlow<List<Order>> {
        val orderList= mutableListOf<Order>()
        val registration= allOrdersRef.addSnapshotListener{result, exception ->
            if(exception!=null){
                close(exception)
            }
            else{
                for(document in result!!){
                    val order=document.toObject(Order::class.java)
                    orderList.add(order)
                }
                trySend(orderList).isSuccess
            }
        }
        awaitClose{
            registration.remove()
        }
    }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay=1
        )
    private val localOrderInitialFlow =
        flow{
            emit(orderManagement.fetchOrder())
        }
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )
    private val ordersIdInitialFlow= callbackFlow<Int> {
        var latestId: Int = 0
        val registration=allOrdersRef
            .orderBy(FieldPath.documentId(), Query.Direction.DESCENDING)
            .limit(1)
            .addSnapshotListener{ result, exception ->
                if(exception!=null){
                    close(exception)
                }
                else{
                    if (result != null) {
                        for (document in result){
                            val product= document.toObject<Product>()
                            latestId=product.id
                        }
                        trySend(latestId).isSuccess
                    }
                }
            }
        awaitClose{
            registration.remove()
        }
    }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay=1
        )

    private val juicesInitialFlow = callbackFlow<List<Product>> {
        val drinkList= mutableListOf<Product>()
        val registration= juicesRef.addSnapshotListener {result, exception ->
            if(exception!=null){
                close(exception)
            }
            else{
                for(document in result!!){
                    val product=document.toObject<Product>()
                    drinkList.add(product)
                }
                trySend(drinkList).isSuccess
            }
        }
        awaitClose {
            registration.remove()
        }
    }

    private val allDrinks= merge(
        allDrinksInitialFlow,
        allDrinksPublisher
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    private val allOrders= merge(
        allOrdersInitialFlow,
        allOrdersPublisher
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    private val localOrder = merge(
        orderLocalPublisher,
        localOrderInitialFlow
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val juices = merge (
        juicesInitialFlow,
        juicesPublisher
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay=1
        )
    private val latestID= merge(
        ordersIdInitialFlow,
        ordersIdPublisher
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay=1
        )


    override fun getLatestOrderId(): SharedFlow<Int> = latestID


    override fun addOrder(order: Order) {
        allOrdersRef.add(order)
    }

    override fun getAllProducts(): SharedFlow<List<Product>> = allDrinks
    override fun getAllOrders(): SharedFlow<List<Order>> = allOrders

    override fun getJuices(): SharedFlow<List<Product>> = juices


    override fun assignToMe(id: Int) {
        val singleOrderRef=allOrdersRef.document(id.toString())
        val updates= hashMapOf(
            "status" to 2
        )
        singleOrderRef.update(updates as Map<String, Any>)
    }

    override fun changeToProcessing(id: Int) {
        TODO("Not yet implemented")
    }

    override fun changeToDelivered(id: Int) {
        TODO("Not yet implemented")
    }

    override fun changeToPaid(id: Int) {
        TODO("Not yet implemented")
    }

    override fun changeToCompleted(id: Int) {
        TODO("Not yet implemented")
    }

    override fun fetchOrder(): SharedFlow<Order> = localOrder

    override fun addDrink(drink: Product) {
        orderManagement.addDrink(drink)
    }

    override fun removeDrink(drink: Product) {
        orderManagement.removeDrink(drink)
    }

    override fun resetOrder() {
        orderManagement.resetOrder()
    }

    override fun setTableNumber(tableNumber: Int) {
        orderManagement.setTableNumber(tableNumber)
    }


}