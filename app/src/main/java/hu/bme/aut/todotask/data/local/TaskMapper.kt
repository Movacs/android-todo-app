package hu.bme.aut.todotask.data.local

import hu.bme.aut.todotask.data.local.entities.TaskEntity
import hu.bme.aut.todotask.domain.model.Task

fun TaskEntity.toDomain(): Task = Task(
    id = id.toLong(),
    title = title,
    description = description,
    priority = priority,
    durationMinutes = durationMinutes,
    isCompleted = isCompleted
)

fun Task.toEntity(): TaskEntity = TaskEntity(
    id = id.toInt(),
    title = title,
    description = description.toString(),
    priority = priority,
    durationMinutes = durationMinutes,
    isCompleted = isCompleted
)