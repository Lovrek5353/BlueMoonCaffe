package com.example.bluemooncaffe.viewModels

import androidx.lifecycle.ViewModel
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.repository.Repository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.SharedFlow


class OrdersViewModel (var drinksRepository: Repository): ViewModel(){
        fun getAllOrders(): SharedFlow<List<Order>> {
            return drinksRepository.getAllOrders()
        }
        fun getMultipleOrders(reference: Int): SharedFlow<List<Order>>{
            return drinksRepository.getMultipleOrders(reference)
        }
        fun getSingleOrder(): SharedFlow<Order>{
            return drinksRepository.getSingleOrder()
        }
        fun assingToMe(id: Int) {
            drinksRepository.assignToMe(id)
        }
        fun changeToProcessing(id: Int) {
            drinksRepository.changeToProcessing(id)
        }
        fun changeToDelivered(id: Int){
            drinksRepository.changeToDelivered(id)
        }
        fun changeToPaid(id: Int){
            drinksRepository.changeToPaid(id)
        }
        fun changeToCompleted(id: Int){
            drinksRepository.changeToCompleted(id)
        }
}
