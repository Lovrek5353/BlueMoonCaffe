package com.example.bluemooncaffe.data

fun getStatus(statusEnum: Int): String{
    when(statusEnum){
        1 -> return "Ordered"
        2 -> return "Assigned"
        3 -> return "In making"
        4 -> return "Delivered"
        5 -> return "Paid"
        6 -> return "Completed"
    }
    return "Error"
}