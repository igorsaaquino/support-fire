package com.example.supportfire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.supportfire.ui.HomeScreen
import com.example.supportfire.ui.screens.BombeiroCivilScreen
import com.example.supportfire.ui.screens.BrigadistaMirimScreen
import com.example.supportfire.ui.screens.RegistrationScreen
import com.example.supportfire.ui.screens.SocorristaScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object AppRoutes {
    const val HOME = "home"
    const val BOMBEIRO_CIVIL_DETAILS = "bombeiro_civil_details"
    const val SOCORRISTA_DETAILS = "socorrista_details"
    const val BRIGADISTA_MIRIM_DETAILS = "brigadista_mirim_details"
    const val REGISTRATION = "registration/{courseName}"

    fun registrationRoute(selectedCourse: String): String {
        val encodedCourse = URLEncoder.encode(selectedCourse, StandardCharsets.UTF_8.toString())
        return "registration/$encodedCourse"
    }
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

        composable(AppRoutes.BOMBEIRO_CIVIL_DETAILS) {
            BombeiroCivilScreen(
                onNavigateToRegistration = { courseName ->
                    navController.navigate(AppRoutes.registrationRoute(courseName))
                },
                onNavigateHome = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.SOCORRISTA_DETAILS) {
            SocorristaScreen(
                onNavigateToRegistration = { courseName ->
                    navController.navigate(AppRoutes.registrationRoute(courseName))
                },
                onNavigateHome = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.BRIGADISTA_MIRIM_DETAILS) {
            BrigadistaMirimScreen(
                onNavigateToRegistration = { courseName ->
                    navController.navigate(AppRoutes.registrationRoute(courseName))
                },
                onNavigateHome = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = AppRoutes.REGISTRATION,
            arguments = listOf(navArgument("courseName") { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedCourseName = backStackEntry.arguments?.getString("courseName") ?: ""
            val courseName = URLDecoder.decode(encodedCourseName, StandardCharsets.UTF_8.toString())

            RegistrationScreen(
                selectedCourse = courseName,
                onRegistrationSuccess = {
                    navController.popBackStack(AppRoutes.HOME, inclusive = false)
                },
                onNavigateHome = {
                    navController.popBackStack()
                }
            )
        }
    }
}
