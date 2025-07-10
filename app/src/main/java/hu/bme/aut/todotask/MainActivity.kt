package hu.bme.aut.todotask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.todotask.data.local.RepositoryProvider
import hu.bme.aut.todotask.data.local.TaskDatabase
import hu.bme.aut.todotask.data.local.repository.TaskRepositoryImpl
import hu.bme.aut.todotask.navigation.SetupNavGraph
import hu.bme.aut.todotask.presentation.task.TaskViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = TaskDatabase.getInstance(applicationContext).dao
        val repository = TaskRepositoryImpl(dao)
        RepositoryProvider.init(repository)
        val taskViewModel = TaskViewModel()
        
        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }
            val onToggleTheme = { isDarkTheme = !isDarkTheme }
            MaterialTheme (colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()) {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, taskViewModel, onToggleTheme)
            }
        }
    }
}
