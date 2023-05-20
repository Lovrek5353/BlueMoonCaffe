package com.example.bluemooncaffe.data


fun totalPrice(items: List<Product>): Double{
    var finalPrice: Double=0.0
    for (item in items){
        finalPrice+=item.price
    }
    return finalPrice
}

fun priceModificator(price: Double): Number{
    val roundedPrice= String.format("%.2f", price)
    return if(roundedPrice.endsWith(".00")){
        roundedPrice.toInt()
    }else{
        roundedPrice.toDouble()
    }
}