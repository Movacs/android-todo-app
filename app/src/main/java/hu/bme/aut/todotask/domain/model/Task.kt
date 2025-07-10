package hu.bme.aut.todotask.domain.model

data class Task(
    val id: Long = 0L,
    val title: String,
    val description: String? = null,
    val priority: Int,
    val durationMinutes: Int,
    val isCompleted: Boolean = false
)