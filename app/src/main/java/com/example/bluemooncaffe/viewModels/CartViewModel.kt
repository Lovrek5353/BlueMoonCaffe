package com.example.bluemooncaffe.viewModels

import androidx.lifecycle.ViewModel
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.repository.Repository
import kotlinx.coroutines.flow.SharedFlow

class CartViewModel (var drinksRepository: Repository): ViewModel() {
    fun getOrder(): SharedFlow<Order>{
        return drinksRepository.fetchOrder()
    }
    fun removeDrink(drink: Product){
        drinksRepository.removeDrink(drink)
    }
    fun addOrder(order: Order){
        order.status=1
        order.waiterId=0
        drinksRepository.addOrder(order)
    }
    fun getOnlineOrder(): SharedFlow<Order>{
        TODO()
    }
}