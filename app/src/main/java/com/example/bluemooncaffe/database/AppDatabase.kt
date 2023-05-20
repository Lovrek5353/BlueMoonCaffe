package com.example.bluemooncaffe.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database (
    entities = [
        ProductEntity::class
    ],
    version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}