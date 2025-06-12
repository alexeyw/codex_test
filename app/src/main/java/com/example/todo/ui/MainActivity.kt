package com.example.todo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.todo.TodoApplication
import com.example.todo.ui.TaskViewModelFactory
import com.example.todo.ui.theme.Material3Theme
import com.example.todo.ui.TodoNavHost

class MainActivity : ComponentActivity() {
    private val viewModelFactory by lazy {
        val repository = (application as TodoApplication).repository
        TaskViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TodoNavHost(viewModelFactory = viewModelFactory)
                }
            }
        }
    }
}
