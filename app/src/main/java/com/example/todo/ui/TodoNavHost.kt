package com.example.todo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.todo.auth.AuthViewModel
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
private const val LOGIN_ROUTE = "login"
private const val SIGNUP_ROUTE = "signup"

@Composable
fun TodoNavHost(viewModelFactory: TaskViewModelFactory, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel(factory = viewModelFactory)
    val authViewModel: AuthViewModel = viewModel()
    val user = authViewModel.user.collectAsState()
    val tasks = viewModel.tasks.collectAsState()

    val start = if (user.value != null) LIST_ROUTE else LOGIN_ROUTE

    NavHost(navController, startDestination = start, modifier = modifier) {
        composable(LOGIN_ROUTE) {
            LoginScreen(
                onLogin = { email, pass ->
                    authViewModel.login(email, pass) { error ->
                        if (error == null) {
                            navController.navigate(LIST_ROUTE) {
                                popUpTo(0)
                            }
                        }
                    }
                },
                onSignup = { navController.navigate(SIGNUP_ROUTE) }
            )
        }
        composable(SIGNUP_ROUTE) {
            SignupScreen(
                onSignup = { email, pass ->
                    authViewModel.signUp(email, pass) { err ->
                        if (err == null) {
                            navController.navigate(LIST_ROUTE) {
                                popUpTo(0)
                            }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(LIST_ROUTE) {
            TaskListScreen(
                viewModel = viewModel,
                onAdd = { navController.navigate(ADD_ROUTE) },
                onEdit = { navController.navigate("edit/${it.id}") },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(LOGIN_ROUTE) {
                        popUpTo(0)
                    }
                }
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
