package br.com.supportfire.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.supportfire.app.ui.HomeScreen

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
            _root_ide_package_.br.com.supportfire.app.ui.screens.BombeiroCivilScreen(
                onNavigateToRegistration = { courseName ->
                    navController.navigate(AppRoutes.registrationRoute(courseName))
                },
                onNavigateHome = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.SOCORRISTA_DETAILS) {
            _root_ide_package_.br.com.supportfire.app.ui.screens.SocorristaScreen(
                onNavigateToRegistration = { courseName ->
                    navController.navigate(AppRoutes.registrationRoute(courseName))
                },
                onNavigateHome = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.BRIGADISTA_MIRIM_DETAILS) {
            _root_ide_package_.br.com.supportfire.app.ui.screens.BrigadistaMirimScreen(
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

            _root_ide_package_.br.com.supportfire.app.ui.screens.RegistrationScreen(
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
