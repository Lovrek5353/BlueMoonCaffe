package com.example.bluemooncaffe.data

data class Product(
    var id: Int = 0,
    var categoryId: Int = 0,
    var name: String = "",
    var price: Double = 0.0,
    var servingSize: Double = 0.0,
    var imageLink: String="",
    var isFavorite: Boolean=false
)
