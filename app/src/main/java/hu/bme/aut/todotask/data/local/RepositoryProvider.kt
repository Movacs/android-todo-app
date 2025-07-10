package hu.bme.aut.todotask.data.local

import hu.bme.aut.todotask.data.local.repository.TaskRepository

object RepositoryProvider {
    lateinit var taskRepository: TaskRepository

    fun init(repository: TaskRepository) {
        taskRepository = repository
    }
}