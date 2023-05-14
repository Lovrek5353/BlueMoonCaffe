package com.example.bluemooncaffe.viewModels


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bluemooncaffe.repository.Repository
import com.google.firebase.auth.AuthResult

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel (var drinksRepository: Repository): ViewModel(){
    fun setTableNumber(tableNumber: Int){
        drinksRepository.setTableNumber(tableNumber)
    }

    fun emailLogin(email: String, password: String): Flow<Result<AuthResult>> {
        return drinksRepository.emailLogin(email, password)
    }
}
