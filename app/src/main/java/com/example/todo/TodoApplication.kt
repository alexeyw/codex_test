package com.example.todo

import android.app.Application
import com.example.todo.data.DefaultTaskRepository
import com.example.todo.data.TaskRepository
import com.example.todo.data.local.TaskDatabase

class TodoApplication : Application() {
    // Lazy initialize database and repository
    val database by lazy { TaskDatabase.getDatabase(this) }
    val repository: TaskRepository by lazy { DefaultTaskRepository(database.taskDao()) }
}
