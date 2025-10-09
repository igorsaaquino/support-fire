package com.example.supportfire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.supportfire.ui.HomeScreen
import com.example.supportfire.ui.screens.RegistrationScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument


object AppRoutes {
    const val HOME = "home"
    const val REGISTRATION = "registration/{selectedCourse}"

    fun registrationRoute(selectedCourse: String) = "registration/$selectedCourse"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                onNavigateToCourseDetails = { courseRoute ->
                    navController.navigate(courseRoute)
                }
            )
        }
        composable(AppRoutes.REGISTRATION,
            arguments = listOf(navArgument("selectedCourse") { type = NavType.StringType })
        ) { backStackEntry ->
            val selectedCourse = backStackEntry.arguments?.getString("selectedCourse") ?: ""
            RegistrationScreen(
                selectedCourse = selectedCourse,
                onRegistrationSuccess = {
                    navController.popBackStack()
                }
            )
        }
    }
}
