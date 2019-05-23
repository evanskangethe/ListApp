package com.example.listapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.listapp.model.Task
import com.example.listapp.model.TaskRepository
import com.example.listapp.model.TaskRoomDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val  repository: TaskRepository
    private val allTask: LiveData<List<Task>>

    init {
        val taskDao = TaskRoomDatabase.getInstance(application).taskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.allTask
    }

    fun getAllTasks():LiveData<List<Task>> {
        return allTask
    }

    fun delete(task: Task){
        repository.deleteTask(task)
    }
}
