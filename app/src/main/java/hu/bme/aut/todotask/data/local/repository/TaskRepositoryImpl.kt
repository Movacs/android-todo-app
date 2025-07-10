package hu.bme.aut.todotask.data.local.repository

import hu.bme.aut.todotask.data.local.dao.TaskDao
import hu.bme.aut.todotask.data.local.entities.TaskEntity
import hu.bme.aut.todotask.data.local.toDomain
import hu.bme.aut.todotask.data.local.toEntity
import hu.bme.aut.todotask.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val dao: TaskDao) : TaskRepository {

    override suspend fun insert(task: Task) {
        dao.insert(task.toEntity())
    }
    override fun getAllTasks(): Flow<List<Task>> =
        dao.getAllTasks().map { list -> list.map { it.toDomain() } }

    override fun getTaskById(id: Int): Flow<Task> =
        dao.getTaskById(id).map { it.toDomain() }

    override suspend fun delete(task: Task) {
        dao.delete(task.toEntity())
    }
}