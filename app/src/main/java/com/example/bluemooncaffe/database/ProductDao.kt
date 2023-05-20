package com.example.bluemooncaffe.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(item: ProductEntity)
    @Query("DELETE FROM products WHERE id=:id")
    fun removeProduct(id: Int)
    @Query("SELECT * FROM products")
    fun getFavoriteDrinks(): MutableList<ProductEntity>
}