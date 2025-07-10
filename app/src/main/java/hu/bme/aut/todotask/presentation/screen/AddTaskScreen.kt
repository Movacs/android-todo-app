package hu.bme.aut.todotask.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import hu.bme.aut.todotask.R
import hu.bme.aut.todotask.domain.model.Task
import hu.bme.aut.todotask.presentation.common.AppHeader

@Composable
fun AddTaskScreen(
    onTaskAdded: (Task) -> Unit,
    onBack: () -> Unit,
    onToggleTheme : () -> Unit
) {
    val scrollState = rememberScrollState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }
    var durationMinutes by remember {mutableStateOf("")}

    val priorityInt = priority.toIntOrNull()
    val durationInt = durationMinutes.toIntOrNull()

    val isTitleValid = title.isNotBlank()
    val isPriorityValid = priorityInt != null && priorityInt in 1..5
    val isDurationValid = durationInt != null && durationInt >= 0

    val isFormValid = isTitleValid && isPriorityValid && isDurationValid


    Scaffold(
        topBar = { AppHeader(title = stringResource(R.string.uj_feladat), onBackClick = onBack, onThemeChange = onToggleTheme) },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
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

                Button(
                    onClick = {
                        val task = Task(
                            title = title,
                            description = description,
                            priority = priority.toInt(),
                            durationMinutes = durationMinutes.toIntOrNull() ?: 0,
                            isCompleted = false
                        )
                        onTaskAdded(task)
                    },
                    enabled = isFormValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        stringResource(R.string.mentes),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }

}