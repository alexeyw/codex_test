package com.example.todo.data

import com.example.todo.data.local.TaskDao
import kotlinx.coroutines.flow.Flow

/**
 * Default implementation of [TaskRepository] using a [TaskDao].
 */
class DefaultTaskRepository(private val dao: TaskDao) : TaskRepository {
    override fun getTasks(): Flow<List<Task>> = dao.getTasks()

    override suspend fun insert(task: Task) {
        dao.insert(task)
    }

    override suspend fun update(task: Task) {
        dao.update(task)
    }

    override suspend fun delete(task: Task) {
        dao.delete(task)
    }
}
