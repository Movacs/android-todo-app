package hu.bme.aut.todotask.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import hu.bme.aut.todotask.R
import hu.bme.aut.todotask.domain.model.Task
import hu.bme.aut.todotask.presentation.common.AppHeader
import hu.bme.aut.todotask.presentation.task.TaskViewModel

@Composable
fun EditTaskScreen(
    taskId: Long,
    viewModel: TaskViewModel,
    onTaskChange: (Task) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onToggleTheme : () -> Unit
) {
    val scrollState = rememberScrollState()
    val taskFlow = remember(taskId) { viewModel.getTaskById(taskId) }
    val task by taskFlow.collectAsState(initial = null)

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }
    var durationMinutes by remember {mutableStateOf("")}
    var isCompleted by remember {mutableStateOf(false)}

    val priorityInt = priority.toIntOrNull()
    val durationInt = durationMinutes.toIntOrNull()

    val isTitleValid = title.isNotBlank()
    val isPriorityValid = priorityInt != null && priorityInt in 1..5
    val isDurationValid = durationInt != null && durationInt >= 0

    val isFormValid = isTitleValid && isPriorityValid && isDurationValid

    Scaffold(
        topBar = { AppHeader(title = task?.title ?: "", onBackClick = onBack, onThemeChange = onToggleTheme) },
    ) { padding ->
        LaunchedEffect(task) {
            task?.let {
                title = it.title
                description = it.description.toString()
                priority = it.priority.toString()
                durationMinutes = it.durationMinutes.toString()
                isCompleted = it.isCompleted
            }
        }
        Column (
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(R.string.cim)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.leiras)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = priority,
                onValueChange = { priority = it },
                label = { Text(stringResource(R.string.prioritas_1_5)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = durationMinutes,
                onValueChange = { durationMinutes = it },
                label = { Text(stringResource(R.string.idotartam_perc)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = { isCompleted = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.kesz), style = MaterialTheme.typography.bodyLarge)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        task?.let {
                            val updatedTask = it.copy(
                                title = title,
                                description = description,
                                durationMinutes = durationMinutes.toInt(),
                                priority = priority.toInt(),
                                isCompleted = isCompleted
                            )
                            onTaskChange(updatedTask)
                            onSave()
                        } },
                    enabled = isFormValid,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.mentes))
                }

            }
        }
    }
}
