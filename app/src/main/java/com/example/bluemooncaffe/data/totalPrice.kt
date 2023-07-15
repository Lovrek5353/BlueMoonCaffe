package com.example.bluemooncaffe.data


fun totalPrice(items: List<Product>): Double {
    var finalPrice: Double = 0.0
    for (item in items) {
        finalPrice += item.price
    }
    return finalPrice
}
