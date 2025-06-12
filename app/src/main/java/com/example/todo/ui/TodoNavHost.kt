package com.example.todo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val LIST_ROUTE = "list"
private const val ADD_ROUTE = "add"
private const val EDIT_ROUTE = "edit/{id}"

@Composable
fun TodoNavHost(viewModelFactory: TaskViewModelFactory, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel(factory = viewModelFactory)
    val tasks = viewModel.tasks.collectAsState()

    NavHost(navController, startDestination = LIST_ROUTE, modifier = modifier) {
        composable(LIST_ROUTE) {
            TaskListScreen(
                viewModel = viewModel,
                onAdd = { navController.navigate(ADD_ROUTE) },
                onEdit = { navController.navigate("edit/${it.id}") }
            )
        }
        composable(ADD_ROUTE) {
            AddEditTaskScreen(onSave = { title ->
                viewModel.addTask(title)
                navController.popBackStack()
            })
        }
        composable(
            EDIT_ROUTE,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val task = tasks.value.firstOrNull { it.id == id }
            AddEditTaskScreen(task = task, onSave = { title ->
                task?.let { viewModel.updateTask(it.copy(title = title)) }
                navController.popBackStack()
            })
        }
    }
}
