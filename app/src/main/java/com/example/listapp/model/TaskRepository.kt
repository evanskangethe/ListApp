package com.example.listapp.model

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {
    val  allTask: LiveData<List<Task>> = taskDao.getAll()

    fun getAllTasks():LiveData<List<Task>> {
        return allTask
    }

    fun deleteAllNotes(){
        DeleteAllNoteAsyncTask(taskDao).execute()
    }

    fun deleteTask(task: Task) {
        DeleteNoteAsyncTask(taskDao).execute(task)
    }

    fun insert(task: Task){
        InsertNoteAsyncTask(taskDao).execute()
    }

    class DeleteAllNoteAsyncTask(private val taskDao: TaskDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            taskDao.deleteAllTasks()
            return null
        }

    }

    class InsertNoteAsyncTask(private val taskDao: TaskDao): AsyncTask<Task, Void, Void>() {
        override fun doInBackground(vararg params: Task): Void? {
            taskDao.insertTask(params[0])
            return null
        }

    }

    class DeleteNoteAsyncTask(private val taskDao: TaskDao): AsyncTask<Task, Void, Void>() {
        override fun doInBackground(vararg params: Task): Void? {
            taskDao.deleteTask(params[0])
            return null
        }

    }
}