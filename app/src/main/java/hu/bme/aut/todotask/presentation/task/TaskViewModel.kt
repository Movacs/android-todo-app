package hu.bme.aut.todotask.presentation.task


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.todotask.data.local.RepositoryProvider
import hu.bme.aut.todotask.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

class TaskViewModel() : ViewModel() {
    private val repository = RepositoryProvider.taskRepository
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTasks().collectLatest { taskList ->
                _tasks.value = taskList
            }
        }
    }
    fun getTaskById(id: Long): Flow<Task?> {
        return repository.getTaskById(id.toInt())
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }
    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }
}
