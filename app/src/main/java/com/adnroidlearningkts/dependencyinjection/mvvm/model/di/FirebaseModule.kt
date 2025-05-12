package com.adnroidlearningkts.dependencyinjection.mvvm.model.di

import android.content.Context
import androidx.room.Room
import com.adnroidlearningkts.dependencyinjection.mvvm.model.room.CartDAO
import com.adnroidlearningkts.dependencyinjection.mvvm.model.room.CartDB
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Dagger/DuplicateBindings:
 * have multiple definitions for how Dagger Hilt should provide an instance of com.google.firebase.firestore.FirebaseFirestore.
 *
 * The Firestore already provided in the HiltFirebaseModule
 */

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

//    @Provides
//    @Singleton
//    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideCartDB(@ApplicationContext context: Context): CartDB {
        return Room.databaseBuilder(context,
                CartDB::class.java,
                        "Ecommerce_Cart_Items"
            ).fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideCartDAO(cartDB: CartDB): CartDAO {
        return cartDB.getCartDAO()
    }
}