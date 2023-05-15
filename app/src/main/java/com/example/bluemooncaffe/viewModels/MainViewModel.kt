package com.example.bluemooncaffe.viewModels

import androidx.lifecycle.ViewModel
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.repository.Repository
import kotlinx.coroutines.flow.SharedFlow

class MainViewModel (var drinksRepository: Repository): ViewModel() {
    fun getAllDrinks(): SharedFlow<List<Product>> {
        return drinksRepository.getAllProducts()
    }
    fun getJuicess(): SharedFlow<List<Product>> {
        return drinksRepository.getJuices()
    }
    fun getBeers(): SharedFlow<List<Product>>{
        return drinksRepository.getBeers()
    }
    fun getCoffees(): SharedFlow<List<Product>>{
        return drinksRepository.getCoffees()
    }
    fun addDrink(drink: Product){
        drinksRepository.addDrink(drink)
    }
}