package hu.bme.aut.todotask.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.bme.aut.todotask.presentation.task.TaskViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import hu.bme.aut.todotask.R
import hu.bme.aut.todotask.presentation.common.AppHeader

@Composable
fun DetailedTaskScreen(
    taskId: Long,
    viewModel: TaskViewModel,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onToggleTheme : () -> Unit
) {
    val scrollState = rememberScrollState()
    val taskFlow = remember(taskId) { viewModel.getTaskById(taskId) }
    val task by taskFlow.collectAsState(initial = null)

    Scaffold(
        topBar = { AppHeader(title = task?.title ?: "", onBackClick = onBack, onThemeChange = onToggleTheme) },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (task != null) {
                Text(
                    text = stringResource(R.string.leiras),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = task!!.description.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(R.string.idotartam_perc),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${task!!.durationMinutes} perc",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(R.string.prioritas_1_5),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = task!!.priority.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(R.string.allapot),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = if (task!!.isCompleted) stringResource(R.string.kesz) else stringResource(R.string.folyamatban),
                    style = MaterialTheme.typography.bodyLarge
                )
                Button(
                    onClick = onEdit
                ) {
                    Text(stringResource(R.string.szerkesztes))
                }
            }

        }
    }
}