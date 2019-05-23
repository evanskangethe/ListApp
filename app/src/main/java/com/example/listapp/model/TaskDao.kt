package com.example.listapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>

    @Query("DELETE FROM task")
    fun deleteAllTasks()

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

}