package com.example.todo.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.todo.data.Task

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow(
        listOf(
            Task(1, "Buy groceries"),
            Task(2, "Walk the dog"),
            Task(3, "Read a book")
        )
    )
    val tasks: StateFlow<List<Task>> = _tasks
}
