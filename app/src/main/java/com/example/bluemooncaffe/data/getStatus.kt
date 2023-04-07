package com.example.bluemooncaffe.data

fun getStatus(statusEnum: Int): String{
    when(statusEnum){
        1 -> return "Ordered"
        2 -> return "In making"
        3 -> return "Delivered"
        4 -> return "Paid"
        5 -> return "Completed"
    }
    return "Error"
}