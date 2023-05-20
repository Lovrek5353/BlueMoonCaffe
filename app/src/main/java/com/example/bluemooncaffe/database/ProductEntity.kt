package com.example.bluemooncaffe.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")
    val id: Int,
    @ColumnInfo(name="categoryId")
    val categoryId: Int,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="price")
    val price: Double,
    @ColumnInfo(name="sertvingSize")
    val servingSize: Double,
    @ColumnInfo(name="imageLink")
    val imageLink: String,
    @ColumnInfo(name="isFavorite")
    val isFavorite: Boolean
)

