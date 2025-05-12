package com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

// Mark as Entity to work with RoomDB
@Entity(tableName = "Ecommerce_Cart_Items")
@Parcelize
data class CategoryProduct(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val price: Double = 0.0,
    val imageUrl: String = ""
): Parcelable
