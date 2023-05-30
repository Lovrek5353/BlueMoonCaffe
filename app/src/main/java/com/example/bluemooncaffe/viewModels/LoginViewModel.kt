package com.example.bluemooncaffe.viewModels


import androidx.lifecycle.ViewModel
import com.example.bluemooncaffe.repository.Repository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class LoginViewModel(var drinksRepository: Repository) : ViewModel() {
    fun setTableNumber(tableNumber: Int) {
        drinksRepository.setTableNumber(tableNumber)
    }

    fun emailLogin(email: String, password: String): Flow<Result<AuthResult>> {
        return drinksRepository.emailLogin(email, password)
    }
}
