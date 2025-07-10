package hu.bme.aut.todotask.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hu.bme.aut.todotask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(title: String, onBackClick: (() -> Unit)? = null, onInfoClick: (() -> Unit)? = null, onThemeChange: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.vissza)
                    )
                }
            }
        },
        actions = {
            if (onInfoClick != null) {
                IconButton(onClick = onInfoClick) {
                    Icon(Icons.Default.Info, contentDescription = stringResource(R.string.nevjegy))
                }
            }
            if (onThemeChange != null) {
                IconButton(onClick = onThemeChange) {
                    Icon(
                        imageVector = Icons.Filled.Lightbulb,
                        contentDescription = stringResource(R.string.temavaltas)
                    )
                }
            }
        }
    )
}