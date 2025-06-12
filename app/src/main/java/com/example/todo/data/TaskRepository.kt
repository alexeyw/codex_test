package com.example.todo.data

import kotlinx.coroutines.flow.Flow

/**
 * Abstraction for accessing and modifying tasks.
 */
interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    suspend fun insert(task: Task)
    suspend fun update(task: Task)
    suspend fun delete(task: Task)
}
