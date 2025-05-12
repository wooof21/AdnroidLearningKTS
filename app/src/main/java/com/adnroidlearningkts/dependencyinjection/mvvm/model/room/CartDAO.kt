package com.adnroidlearningkts.dependencyinjection.mvvm.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct

@Dao
interface CartDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: CategoryProduct)

    @Query("SELECT * FROM Ecommerce_Cart_Items")
    suspend fun getAllCartItems(): List<CategoryProduct>

    @Query("DELETE FROM Ecommerce_Cart_Items WHERE id = :productId")
    suspend fun removeFromCart(productId: String)

    @Query("DELETE FROM Ecommerce_Cart_Items")
    suspend fun clearCart()
}