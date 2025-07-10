package hu.bme.aut.todotask.navigation
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.navArgument
import hu.bme.aut.todotask.presentation.screen.AboutScreen
import hu.bme.aut.todotask.presentation.screen.AddTaskScreen
import hu.bme.aut.todotask.presentation.screen.DetailedTaskScreen
import hu.bme.aut.todotask.presentation.screen.EditTaskScreen
import hu.bme.aut.todotask.presentation.screen.ListTaskScreen
import hu.bme.aut.todotask.presentation.task.TaskViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: TaskViewModel, onToggleTheme : () -> Unit) {

    NavHost(
        navController = navController,
        startDestination = Screen.ListTaskScreen.route
    ) {
        composable(route = Screen.ListTaskScreen.route) {

            val tasks by viewModel.tasks.collectAsState()
            ListTaskScreen(
                tasks,
                onDeleteTask = {task -> viewModel.deleteTask(task)},
                onTaskClick = {taskId -> navController.navigate(Screen.DetailTaskScreen.passId(taskId))},
                onAddTaskClick = {navController.navigate(Screen.AddTaskScreen.route)},
                onInfoClick = {navController.navigate(Screen.AboutScreen.route)},
                onToggleTheme = onToggleTheme
            )
        }
        composable(
            route = Screen.DetailTaskScreen.route,
            arguments = listOf(navArgument("taskId") { type = NavType.LongType })
        ) { backStackEntry ->

            val taskId = backStackEntry.arguments?.getLong("taskId") ?: return@composable
            DetailedTaskScreen(
                taskId = taskId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onEdit = {navController.navigate(Screen.EditTaskScreen.passId(taskId))},
                onToggleTheme = onToggleTheme
            )
        }

        composable(route = Screen.AddTaskScreen.route) {

            AddTaskScreen(
                onTaskAdded = { task ->
                viewModel.addTask(task)
                navController.popBackStack()
                },
                onBack = {navController.popBackStack()},
                onToggleTheme = onToggleTheme
            )
        }

        composable(
            route = Screen.EditTaskScreen.route,
            arguments = listOf(navArgument("taskId") { type = NavType.LongType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getLong("taskId") ?: return@composable

            EditTaskScreen(
                taskId = taskId,
                viewModel = viewModel,
                onTaskChange = { updatedTask ->
                    viewModel.updateTask(updatedTask)
                },
                onSave = {
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                },
                onToggleTheme = onToggleTheme
            )
        }

        composable(route = Screen.AboutScreen.route) {
            AboutScreen(onBackClick = {navController.popBackStack()},onToggleTheme = onToggleTheme)
        }

    }
}
