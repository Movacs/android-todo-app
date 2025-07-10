package hu.bme.aut.todotask.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hu.bme.aut.todotask.R
import hu.bme.aut.todotask.presentation.common.AppHeader

@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
    onToggleTheme : () -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { AppHeader(
            title = stringResource(R.string.informaciok), onBackClick = onBackClick, onThemeChange = onToggleTheme
        ) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = stringResource(R.string.info),
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                stringResource(R.string.todotask_applikacio),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                stringResource(R.string.verzio_1_0_0),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                stringResource(R.string.keszitette_kovacs_marcell),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
