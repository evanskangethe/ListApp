package com.example.listapp.model

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Task::class],version = 1)
abstract class TaskRoomDatabase :RoomDatabase(){

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getInstance(context: Context): TaskRoomDatabase {
            var tempInstance = INSTANCE

            if (tempInstance != null ) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database")
                    .addCallback(TaskDatabaseCallback())
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                return instance
            }
        }

        private class TaskDatabaseCallback: RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let {
                    database -> PopulateDbAsyncTask(database.taskDao()).execute()
                }
            }
        }

        class PopulateDbAsyncTask(private val taskDao: TaskDao): AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                taskDao.deleteAllTasks()
                taskDao.insertTask(Task(title = "Learn Kotlin",completed = true))
                taskDao.insertTask(Task(title = "Learn Android",completed = false))
                taskDao.insertTask(Task(title = "Learn Java",completed = true))
                taskDao.insertTask(Task(title = "Learn SpringBoot",completed = false))
                return null
            }

        }
    }
}