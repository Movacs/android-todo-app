package hu.bme.aut.todotask.data.local.repository
import hu.bme.aut.todotask.data.local.entities.TaskEntity
import hu.bme.aut.todotask.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(task: Task)
    fun getAllTasks(): Flow<List<Task>>
    fun getTaskById(id: Int): Flow<Task>
    suspend fun delete(task: Task)
}