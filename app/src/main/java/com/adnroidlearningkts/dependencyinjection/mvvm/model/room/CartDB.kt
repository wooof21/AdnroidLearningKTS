package com.adnroidlearningkts.dependencyinjection.mvvm.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct

@Database(entities = [CategoryProduct::class], version = 6, exportSchema = false)
abstract class CartDB: RoomDatabase() {

    abstract fun getCartDAO(): CartDAO
}