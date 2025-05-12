package com.adnroidlearningkts.mvvm.roomdb.contactmanager.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * @Database: used to declare as a room database and configure it properties
 *
 *  2 params: entity & version
 *      * entities: an array of entity classes tha represents the tables in the database
 *      * version: integer num represent the database version.
 *          - when make changes to database schema, version num need to be increment to trigger
 *              a migration
 */
@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {

    /**
     * Typically define DAO as abstract methods here
     *
     * Room will auto generate the code to implement this method
     */
    abstract val contactDAO: ContactDAO

    /**
     * Singleton Design Pattern
     *
     * `companion object`: used to define a static member in a class
     *
     * @Volatile: used to mark the INSTANCE variable to ensure that read and write to this are atomic
     *      - helps to prevent any possible race condition that may occur during multi-threaded
     *          operations
     *      - thread safety: In a multi-threaded environment, multiple threads might try to access and initialize `INSTANCE` simultaneously.
     *          - it ensures that writes to `INSTANCE` are immediately visible to all threads.
     *          - Without it, one thread might write to `INSTANCE`, but other threads might not see
     *              the updated value (they could be reading a cached, outdated value).
     *          - it prevents caching of the `INSTANCE` variable at the thread level.
     *
     * `synchronized`: only one thread can access the function at a time
     *      - avoid potential issues when multiple threads try to access or modify the INSTANCE variable
     *          concurrently
     */
    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase {
            /**
             * INSTANCE ?: ... means: "If INSTANCE is not null, return it; otherwise, execute the code on the right side of ?:"
             *
             * The code inside synchronized now creates a local instance variable,
             * which is a best practice to only synchronize what is needed.
             *
             * The result of the room database builder is saved to a variable instance and then assigned to INSTANCE.
             * Then, instance is returned.
             *      * This correctly sets the singleton and prevents potential issues if
             *          multiple threads enter the synchronized block very quickly.
             *          - The singleton works as it should, being only created once.
             *          - Avoids rare race conditions.
             */
            return INSTANCE ?: synchronized(this) {
                //create a new instance of database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contacts_db" //database name, the file name
                ).build()
                INSTANCE = instance
                //When a lambda's last line is the return value it can be written like this.
                instance
            }
        }
    }
}