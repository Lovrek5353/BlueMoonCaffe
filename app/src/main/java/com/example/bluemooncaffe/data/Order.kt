package com.example.bluemooncaffe.data

class Order (
    var id: Int=0,
    var products: MutableList<Product> = mutableListOf(),
    var status: Int=0,
    var totalPrice: Double=0.0,
    var tableNumber: Int=0,
    var waiterId: Int=0,
)

