package hu.bme.aut.todotask.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description : String,
    val priority: Int,
    @ColumnInfo(name = "duration_minutes") val durationMinutes: Int = 0,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean

)

