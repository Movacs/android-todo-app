package hu.bme.aut.todotask.navigation

sealed class Screen(val route: String) {

    object ListTaskScreen : Screen(route = "list")
    object DetailTaskScreen : Screen(route = "detail/{taskId}") {
        fun passId(taskId: Long): String = "detail/$taskId"
    }
    object AddTaskScreen : Screen(route = "add")

    object EditTaskScreen : Screen(route = "edit/{taskId}") {
        fun passId(taskId: Long): String = "edit/$taskId"
    }
    object AboutScreen : Screen(route = "about")
}