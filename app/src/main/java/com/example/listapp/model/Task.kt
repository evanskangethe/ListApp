package com.example.listapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var title: String,
    var completed: Boolean = false
)