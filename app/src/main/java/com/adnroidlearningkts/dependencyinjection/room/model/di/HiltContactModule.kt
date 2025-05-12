package com.adnroidlearningkts.dependencyinjection.room.model.di

import android.content.Context
import androidx.room.Room
import com.adnroidlearningkts.dependencyinjection.room.model.room.HiltContactDAO
import com.adnroidlearningkts.dependencyinjection.room.model.room.HiltContactDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltContactModule {

    /**
     * Use Hilt to provide the singleton instance of DB
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HiltContactDB {
        return Room.databaseBuilder(
            context,
            HiltContactDB::class.java,
            "hilt_contacts"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDAO(hiltDB: HiltContactDB): HiltContactDAO {
        return hiltDB.getHiltContactDAO()
    }
}