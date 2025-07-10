package hu.bme.aut.todotask.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.bme.aut.todotask.domain.model.Task
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import hu.bme.aut.todotask.R
import hu.bme.aut.todotask.presentation.common.AppHeader

@Composable
fun ListTaskScreen(
    tasks: List<Task>,
    onDeleteTask: (Task) -> Unit,
    onTaskClick :(Long) -> Unit,
    onAddTaskClick: () -> Unit,
    onInfoClick: () -> Unit,
    onToggleTheme : () -> Unit
) {
    Scaffold(
        topBar = { AppHeader(title = stringResource(R.string.feladatok),onInfoClick = onInfoClick, onThemeChange = onToggleTheme) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTaskClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_task)
                )
            }
        }
    ) {
            padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks, key = { it.id }) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTaskClick(task.id) },

                    elevation = CardDefaults.cardElevation(),

                    ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        IconButton(onClick = { onDeleteTask(task) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(R.string.torles),
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }

    }
}