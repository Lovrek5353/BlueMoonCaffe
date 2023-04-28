package com.example.bluemooncaffe.viewModels

import androidx.lifecycle.ViewModel
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.repository.Repository
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.SharedFlow

class CartViewModel (var drinksRepository: Repository): ViewModel() {
    fun getOrder(): SharedFlow<Order>{
        return drinksRepository.fetchOrder()
    }
    fun removeDrink(drink: Product){
        drinksRepository.removeDrink(drink)
    }
    fun setOrderId(id: Int){
        drinksRepository.setOrderId(id)
    }
    fun addOrder(order: Order){
        order.status=1
        order.waiterId=0
        drinksRepository.addOrder(order)
    }
    fun getOnlineOrder(): SharedFlow<Order>{
        return drinksRepository.getSingleOrder()
    }
    fun getOrderId(): SharedFlow<Int>{
        return drinksRepository.getLatestOrderId()
    }
    fun setOrderTimeStamp(time: Timestamp){
        drinksRepository.setTimeStamp(time)
    }
}