package com.adnroidlearningkts.mvvm.roomdb.notetaking.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    /**
     * Room Generated Code: When build the Android project, Room automatically generates
     * a concrete implementation of the abstract `NoteDatabase` class.
     * This generated class provides the actual code for the abstract methods like getNoteDao(),
     * returning a Room-generated instance of the `NoteDAO` interface.
     */
    abstract fun getNoteDao(): NoteDAO


    companion object {
        /**
         * @Volatile: write to this field are immediately made visible to other threads
         */
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * This creates a private, immutable object used as a lock for synchronization.
         * This is a common practice in Kotlin to ensure that only one thread can
         * access a critical section of code at a time, preventing race conditions during database instance creation.
         */
        private val LOCK = Any()

//        fun getInstance(context: Context): NoteDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    NoteDatabase::class.java,
//                    "notes_db"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }

        /**
         * synchronized: used on multi-threading, prevent any unsynchronized data between threads
         * ensure there is only one instance of NoteDatabase
         *
         * `operator` keyword: used to mark a function as an operator function. This allows to use
         * symbolic notations (like +, -, *, /, [], etc.) to call that function
         *
         * Kotlin has a predefined set of operators that can be overloaded.
         * To implement an operator for your own class or an existing class (using extension functions),
         * you define a function with a specific name that corresponds to the operator you want to overload,
         * and you mark this function with the `operator` keyword.
         *
         * When the Kotlin compiler encounters an expression using an overloaded operator,
         * it translates that expression into a call to the corresponding operator function
         *
         * e.g.
         *  - The + operator for binary addition corresponds to the `plus` function.
         *  - The [] operator for indexing corresponds to the get and `set` functions.
         *  - The () operator for function invocation corresponds to the `invoke` function.
         *
         *  The `operator` fun invoke(...) allows to call the companion object like
         *      a function (NoteDatabase(context)) to get the database instance
         */
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            /**
             * The `also` function is a scope function that executes the given block on the object
             * it's called on (createDB(context) result) and returns the object itself.
             * Here, it's used to assign the newly created database instance to INSTANCE after it has been created.
             */
            INSTANCE ?: createDB(context).also {
                INSTANCE = it
            }
        }

        private fun createDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notes_db"
        ).build()
    }


}