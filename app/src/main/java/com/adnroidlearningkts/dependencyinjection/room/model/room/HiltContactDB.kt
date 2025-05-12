package com.adnroidlearningkts.dependencyinjection.room.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [HiltContact::class], version = 1)
abstract class HiltContactDB: RoomDatabase() {

    //provide DAO instance: Room auto implement this method
    abstract fun getHiltContactDAO(): HiltContactDAO

    /**
     * Instead of manually create the singleton DB instance, use Hilt to manage the creation of singleton DB
     * instance in Module class
     */

//    companion object {
//        @Volatile
//        private var INSTANCE: HiltContactDB? = null
//
//        fun getInstance(context: Context): HiltContactDB {
//            return INSTANCE ?: synchronized(this) {
//                //create a new instance of database
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    HiltContactDB::class.java,
//                    "hilt_contacts" //database name, the file name
//                ).build()
//                INSTANCE = instance
//                //When a lambda's last line is the return value it can be written like this.
//                instance
//            }
//        }
//    }
}