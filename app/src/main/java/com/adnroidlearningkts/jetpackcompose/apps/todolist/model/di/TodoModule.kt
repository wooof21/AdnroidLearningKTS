package com.adnroidlearningkts.jetpackcompose.apps.todolist.model.di

import android.content.Context
import androidx.room.Room
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.room.TodoDatabase
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Constants.TODO_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    @Singleton
    fun provideTodoDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            TODO_DB_NAME
        ).build()

    @Provides
    @Singleton
    fun provideTodoDAO(db: TodoDatabase) = db.getTodoDAO()
}