package com.example.bluemooncaffe.data

class OrderManagement {
    var order = Order()

    fun fetchOrder(): Order{
        return order
    }

    fun addDrink(drink: Product){
        order.products.add(drink)
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
}