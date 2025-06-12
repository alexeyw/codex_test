package com.example.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing a single task.
 */
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String
)
