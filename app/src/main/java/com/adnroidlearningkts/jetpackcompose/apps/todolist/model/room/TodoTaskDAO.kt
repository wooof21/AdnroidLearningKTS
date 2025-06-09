package com.adnroidlearningkts.jetpackcompose.apps.todolist.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TodoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoTaskDAO {

    /**
     * Flow: run async by default
     *
     * When return the Flow data, Room can also automatically push update to this Flow, Add, Update, Delete
     */
    @Query("SELECT * FROM Todo_Compose ORDER BY id ASC")
    fun getAllTasks(): Flow<List<TodoTask>>

    @Query("SELECT * FROM Todo_Compose WHERE id=:taskId")
    fun getTaskById(taskId: Int): Flow<TodoTask?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TodoTask)

    @Update
    suspend fun updateTask(task: TodoTask)

    @Delete
    suspend fun deleteTask(task: TodoTask)

    @Query("DELETE FROM Todo_Compose")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM Todo_Compose WHERE title LIKE :query OR description LIKE :query")
    fun searchTask(query: String): Flow<List<TodoTask>>

    //Sort the tasks start first with letter `L`, then letter `M', then letter `H`, then NONE
//    @Query("SELECT * FROM Todo_Compose ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 " +
//            "WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 WHEN priority LIKE 'N%' THEN 4 END")
//    fun sortByLowPriority(): Flow<List<TodoTask>>
//
//    @Query("SELECT * FROM Todo_Compose ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 " +
//            "WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 WHEN priority LIKE 'N%' THEN 4 END")
//    fun sortByHighPriority(): Flow<List<TodoTask>>

    //can also use triple quote """ to write the query in separate line
    @Query("""
        SELECT * FROM Todo_Compose ORDER BY
        CASE 
            WHEN priority LIKE 'L%' THEN 1
            WHEN priority LIKE 'M%' THEN 2
            WHEN priority LIKE 'H%' THEN 3
            WHEN priority LIKE 'N%' THEN 4 
        END
    """)
    fun sortByLowPriority(): Flow<List<TodoTask>>

    @Query("""
        SELECT * FROM Todo_Compose ORDER BY
        CASE 
            WHEN priority LIKE 'H%' THEN 1
            WHEN priority LIKE 'M%' THEN 2
            WHEN priority LIKE 'L%' THEN 3
            WHEN priority LIKE 'N%' THEN 4 
        END
    """)
    fun sortByHighPriority(): Flow<List<TodoTask>>
}