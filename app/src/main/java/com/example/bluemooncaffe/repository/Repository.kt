package com.example.bluemooncaffe.repository

import android.util.Log
import com.example.bluemooncaffe.data.*
import com.example.bluemooncaffe.database.ProductDao
import com.example.bluemooncaffe.network.CocktailAPI

import com.google.firebase.Timestamp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.auth.User
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

    fun getSingleOrder(): SharedFlow<Order>

    fun getLatestOrderId(): SharedFlow<Int>

    fun getJuices(): SharedFlow<List<Product>>
    fun getBeers(): SharedFlow<List<Product>>
    fun getCoffees(): SharedFlow<List<Product>>

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
    fun setOrderId(id: Int)
    fun setTimeStamp(time: Timestamp)

    fun emailLogin(email: String, password: String): Flow<Result<AuthResult>>

    fun removeFromFavorite(item: Product)
    fun addToFavorite(item: Product)
    fun getFavoriteDrinks(): SharedFlow<List<Product>>

    fun getCocktail(): SharedFlow<List<Cocktail>>
    fun getUncompletedOrders(): SharedFlow<List<Order>>
}

internal class RepositoryImpl(
    private val orderManagement: OrderManagement,
    private val productDao: ProductDao,
    private val cocktailAPI: CocktailAPI
) : Repository {
    private val flowScope = CoroutineScope(Dispatchers.Default)

    private val db = Firebase.firestore
    val storage = Firebase.storage.reference
    private val auth= FirebaseAuth.getInstance()

    val allDrinksRef = db.collection("drinks")
    val allOrdersRef = db.collection("orders")
    val uncompletedOrdersRef=db.collection("orders").whereNotEqualTo("status",6)

    val juicesRef = db.collection("products").whereEqualTo("categoryId", 100)
    val beerRef=db.collection("products").whereEqualTo("categoryId", 101)
    val coffeesRef=db.collection("products").whereEqualTo("categoryId", 102)


    val allDrinksPublisher = MutableSharedFlow<List<Product>>()
    val orderLocalPublisher = MutableSharedFlow<Order>()
    val ordersIdPublisher = MutableSharedFlow<Int>()
    val favoriteItemsPublisher = MutableSharedFlow<List<Product>>()
    val cocktailInitialPublisher= MutableSharedFlow<List<Cocktail>>()

    val juicesPublisher = MutableSharedFlow<List<Product>>()
    val beerPublisher = MutableSharedFlow<List<Product>>()
    val coffeesPublisher = MutableSharedFlow<List<Product>>()

    private val allDrinksInitialFlow = callbackFlow<List<Product>> {
        val drinkList = mutableListOf<Product>()
        val registration = allDrinksRef.addSnapshotListener { result, exception ->
            if (exception != null) {
                close(exception)
            } else {
                for (document in result!!) {
                    val product = document.toObject<Product>()
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


    private val localOrderInitialFlow =
        flow {
            emit(orderManagement.fetchOrder())
        }
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )
    private val ordersIdInitialFlow = callbackFlow<Int> {
        var latestId: Int = 0
        val registration = allOrdersRef
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(1)
            .addSnapshotListener { result, exception ->
                if (exception != null) {
                    close(exception)
                } else {
                    if (result != null) {
                        for (document in result) {
                            val product = document.toObject<Product>()
                            latestId = product.id
                        }
                        trySend(latestId).isSuccess
                    }
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

    private val favoriteItemsInitialFlow =
        flow{emit(productDao.getFavoriteDrinks().map{it.ToProduct()})}
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )
    private val juicesInitialFlow = callbackFlow<List<Product>> {
        val drinkList = mutableListOf<Product>()
        val registration = juicesRef.addSnapshotListener { result, exception ->
            if (exception != null) {
                close(exception)
            } else {
                for (document in result!!) {
                    val product = document.toObject<Product>()
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
    private val beerInitialFlow = callbackFlow<List<Product>> {
        val drinkList= mutableListOf<Product>()
        val registration=beerRef.addSnapshotListener{result, exception ->
            if(exception != null){
                close(exception)
            }
            else{
                for(document in result!!){
                    val product= document.toObject<Product>()
                    drinkList.add(product)
                }
                trySend(drinkList).isSuccess
            }
        }
        awaitClose{
            registration.remove()
        }
    }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    private val coffeesInitialFlow = callbackFlow<List<Product>> {
        val drinkList= mutableListOf<Product>()
        val registration=coffeesRef.addSnapshotListener{result, exception ->
            if(exception != null){
                close(exception)
            }
            else{
                for(document in result!!){
                    val product= document.toObject<Product>()
                    drinkList.add(product)
                }
                trySend(drinkList).isSuccess
            }
        }
        awaitClose{
            registration.remove()
        }
    }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val cocktailInitialFlow =
        flow{
            emit(cocktailAPI.fetchRandomCocktail().initialObject.map{it.ToCocktail()})
        }
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )

    private val allDrinks = merge(
        allDrinksInitialFlow,
        allDrinksPublisher
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
    private val favoriteItems = merge(
        favoriteItemsInitialFlow,
        favoriteItemsPublisher
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    private val juices = merge(
        juicesInitialFlow,
        juicesPublisher
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    private val beers= merge(
        beerPublisher,
        beerInitialFlow
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
    )
    private val coffees=merge(
        coffeesInitialFlow,
        coffeesPublisher
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val latestID = merge(
        ordersIdInitialFlow,
        ordersIdPublisher
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    private val cocktail = merge(
        cocktailInitialPublisher,
        cocktailInitialFlow
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )


    override fun getLatestOrderId(): SharedFlow<Int> = latestID


    override fun addOrder(order: Order) {
        allOrdersRef.add(order)
    }

    override fun getAllProducts(): SharedFlow<List<Product>> = allDrinks
    override fun getAllOrders(): SharedFlow<List<Order>> {
        val allOrdersPublisher = MutableSharedFlow<List<Order>>()
        val orderList = mutableListOf<Order>()

        val allOrdersInitialFlow = callbackFlow<List<Order>> {
            val registration = allOrdersRef
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener(MetadataChanges.INCLUDE) { result, exception ->
                    if (exception != null) {
                        close(exception)
                    } else {
                        for (change in result?.documentChanges!!) {
                            val order = change.document.toObject(Order::class.java)

                            when (change.type) {
                                DocumentChange.Type.ADDED -> {
                                    orderList.add(0, order)
                                }
                                DocumentChange.Type.MODIFIED -> {
                                    val index = orderList.indexOfFirst { it.id == order.id }
                                    orderList[index] = order
                                }
                                DocumentChange.Type.REMOVED -> {
                                    val index = orderList.indexOfFirst { it.id == order.id }
                                    orderList.removeAt(index)
                                }
                            }
                        }
                        trySend(orderList.toList()).isSuccess
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

        val allOrders = merge(
            allOrdersInitialFlow,
            allOrdersPublisher
        )
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )

        return allOrders
    }


    override fun getSingleOrder(): SharedFlow<Order> {
        val singleOrderPublisher = MutableSharedFlow<Order>()
        val singleOrderInitialFlow = callbackFlow<Order> {
            var order: Order = Order()
            val registration = allOrdersRef
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .addSnapshotListener { result, exception ->
                    if (exception != null) {
                        close(exception)
                    } else {
                        for (document in result!!) {
                            order = document.toObject(Order::class.java)
                        }
                        trySend(order).isSuccess
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
        val singleOrder = merge(
            singleOrderPublisher,
            singleOrderInitialFlow
        )
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )
        return singleOrder
    }

    override fun getJuices(): SharedFlow<List<Product>> = juices
    override fun getBeers(): SharedFlow<List<Product>> = beers
    override fun getCoffees(): SharedFlow<List<Product>> = coffees

    override fun assignToMe(id: Int) {   //change to actual waiterId
        val updates = hashMapOf(
            "waiterId" to 50,
            "status" to 2
        )
        val tempref = allOrdersRef.document(id.toString())
        val query = allOrdersRef.whereEqualTo("id", id).limit(1)
        query.get().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val snapshot = result.getResult()
                for (documentSnapshot in snapshot!!.documents) {
                    val documentRef = documentSnapshot.reference
                    documentRef.update(updates as Map<String, Any>)
                }
            } else {
                Log.d("Error", result.exception.toString())
            }
        }
    }

    override fun changeToProcessing(id: Int) {
        val updates = hashMapOf(
            "status" to 3
        )
        val tempref = allOrdersRef.document(id.toString())
        val query = allOrdersRef.whereEqualTo("id", id).limit(1)
        query.get().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val snapshot = result.getResult()
                for (documentSnapshot in snapshot!!.documents) {
                    val documentRef = documentSnapshot.reference
                    documentRef.update(updates as Map<String, Any>)
                }
            } else {
                Log.d("Error", result.exception.toString())
            }
        }
    }

    override fun changeToDelivered(id: Int) {
        val updates = hashMapOf(
            "status" to 4
        )
        val tempref = allOrdersRef.document(id.toString())
        val query = allOrdersRef.whereEqualTo("id", id).limit(1)
        query.get().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val snapshot = result.getResult()
                for (documentSnapshot in snapshot!!.documents) {
                    val documentRef = documentSnapshot.reference
                    documentRef.update(updates as Map<String, Any>)
                }
            } else {
                Log.d("Error", result.exception.toString())
            }
        }
    }

    override fun changeToPaid(id: Int) {
        val updates = hashMapOf(
            "status" to 5
        )
        val tempref = allOrdersRef.document(id.toString())
        val query = allOrdersRef.whereEqualTo("id", id).limit(1)
        query.get().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val snapshot = result.getResult()
                for (documentSnapshot in snapshot!!.documents) {
                    val documentRef = documentSnapshot.reference
                    documentRef.update(updates as Map<String, Any>)
                }
            } else {
                Log.d("Error", result.exception.toString())
            }
        }
    }

    override fun changeToCompleted(id: Int) {
        val updates = hashMapOf(
            "status" to 6
        )
        val tempref = allOrdersRef.document(id.toString())
        val query = allOrdersRef.whereEqualTo("id", id).limit(1)
        query.get().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                val snapshot = result.getResult()
                for (documentSnapshot in snapshot!!.documents) {
                    val documentRef = documentSnapshot.reference
                    documentRef.update(updates as Map<String, Any>)
                }
            } else {
                Log.d("Error", result.exception.toString())
            }
        }
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

    override fun setOrderId(id: Int) {
        val temp = id + 1
        orderManagement.setOrderID(temp)
    }

    override fun setTimeStamp(time: Timestamp) {
        orderManagement.setTimeStamp(time)
    }

    override fun emailLogin(email: String, password: String): Flow<Result<AuthResult>> = callbackFlow {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    trySend(Result.success(task.result)).isSuccess
                }
                else{
                    trySend(Result.failure(task.exception!!)).isSuccess
                }
                close()
            }
        awaitClose()
    }

    override fun addToFavorite(item: Product) {
        productDao.insertProduct(item.toProductEntity())
    }

    override fun removeFromFavorite(item: Product) {
        productDao.removeProduct(item.id)
    }

    override fun getFavoriteDrinks(): SharedFlow<List<Product>> =favoriteItems

    override fun getCocktail(): SharedFlow<List<Cocktail>> = cocktail
    override fun getUncompletedOrders(): SharedFlow<List<Order>> {
        val allOrdersPublisher = MutableSharedFlow<List<Order>>()
        val orderList = mutableListOf<Order>()

        val allOrdersInitialFlow = callbackFlow<List<Order>> {
            val registration = uncompletedOrdersRef
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener(MetadataChanges.INCLUDE) { result, exception ->
                    if (exception != null) {
                        close(exception)
                    } else {
                        for (change in result?.documentChanges!!) {
                            val order = change.document.toObject(Order::class.java)

                            when (change.type) {
                                DocumentChange.Type.ADDED -> {
                                    orderList.add(0, order)
                                }
                                DocumentChange.Type.MODIFIED -> {
                                    val index = orderList.indexOfFirst { it.id == order.id }
                                    orderList[index] = order
                                }
                                DocumentChange.Type.REMOVED -> {
                                    val index = orderList.indexOfFirst { it.id == order.id }
                                    orderList.removeAt(index)
                                }
                            }
                        }
                        trySend(orderList.toList()).isSuccess
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

        val allOrders = merge(
            allOrdersInitialFlow,
            allOrdersPublisher
        )
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )

        return allOrders
    }
}