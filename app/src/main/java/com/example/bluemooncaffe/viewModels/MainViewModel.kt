package com.example.bluemooncaffe.viewModels

import androidx.lifecycle.ViewModel
import com.example.bluemooncaffe.data.Cocktail
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

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
    fun addToFavorite(item: Product){
        CoroutineScope(Dispatchers.IO).launch {
            drinksRepository.addToFavorite(item)
        }
    }
    fun removeFromFavorite(item: Product){
        CoroutineScope(Dispatchers.IO).launch {
            drinksRepository.removeFromFavorite(item)
        }
    }
    fun getFavoriteItems(): SharedFlow<List<Product>>{
        return drinksRepository.getFavoriteDrinks()
    }
    fun getCocktail(): SharedFlow<List<Cocktail>>{
        return drinksRepository.getCocktail()
    }
}