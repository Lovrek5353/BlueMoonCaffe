package com.example.bluemooncaffe.viewModels

import androidx.lifecycle.ViewModel
import com.example.bluemooncaffe.repository.Repository

class LoginViewModel (var drinksRepository: Repository): ViewModel(){
    fun setTableNumber(tableNumber: Int){
        drinksRepository.setTableNumber(tableNumber)
    }
}
