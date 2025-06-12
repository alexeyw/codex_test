package com.example.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.data.Task

@Composable
fun AddEditTaskScreen(
    task: Task? = null,
    onSave: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember(task) { mutableStateOf(task?.title ?: "") }

    Column(modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Task") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { if (title.isNotBlank()) onSave(title) },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Save")
        }
    }
}
