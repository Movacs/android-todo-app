package hu.bme.aut.todotask.data.local.dao

import androidx.room.*
import hu.bme.aut.todotask.data.local.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id =:id")
    fun getTaskById(id: Int): Flow<TaskEntity>
}
