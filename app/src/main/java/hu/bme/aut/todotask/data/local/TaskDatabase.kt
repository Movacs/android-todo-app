package hu.bme.aut.todotask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.bme.aut.todotask.data.local.dao.TaskDao
import hu.bme.aut.todotask.data.local.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract val dao: TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}