package com.example.bluemooncaffe.data

import com.google.firebase.Timestamp

class OrderManagement {
    var order = Order()

    fun fetchOrder(): Order{
        return order
    }

    fun addDrink(drink: Product){
        order.products.add(drink)
    }

    fun setOrderID(id: Int){
        order.id=id
    }

    fun removeDrink(drink: Product){
        order.products.remove(drink)
    }

    fun resetOrder(){
        order= Order()
    }
    fun setTableNumber(tableNumber: Int){
        order.tableNumber=tableNumber
    }
    fun setTimeStamp(time: Timestamp){
        order.timestamp=time
    }
}