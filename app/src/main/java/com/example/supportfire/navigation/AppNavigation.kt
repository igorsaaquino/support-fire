package com.example.supportfire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.supportfire.ui.HomeScreen
import com.example.supportfire.ui.RegistrationScreen

object AppRoutes {
    const val HOME = "home"
    const val REGISTRATION = "registration"
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
                onNavigateToRegistration = {
                    navController.navigate(AppRoutes.REGISTRATION)
                }
            )
        }
        composable(AppRoutes.REGISTRATION) {
            RegistrationScreen(
                onRegistrationSuccess = {
                    navController.popBackStack() // Volta para a tela Home
                }
            )
        }
    }
}
