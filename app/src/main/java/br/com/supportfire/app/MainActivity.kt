package br.com.supportfire.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.supportfire.app.ui.HomeScreen
import com.supportfire.supportfire.ui.theme.SupportFireTheme
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupportFireTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {

                        composable("home") {
                            HomeScreen(
                                onNavigateToCourseDetails = { courseRoute ->
                                    navController.navigate(courseRoute)
                                }
                            )
                        }

                        composable("bombeiro_civil_details") {
                            _root_ide_package_.br.com.supportfire.app.ui.screens.BombeiroCivilScreen(
                                onNavigateToRegistration = { courseName ->
                                    val encodedCourseName = URLEncoder.encode(
                                        courseName,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate("registration/$encodedCourseName")
                                },
                                onNavigateHome = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("socorrista_details") {
                            _root_ide_package_.br.com.supportfire.app.ui.screens.SocorristaScreen(
                                onNavigateToRegistration = { courseName ->
                                    val encodedCourseName = URLEncoder.encode(
                                        courseName,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate("registration/$encodedCourseName")
                                },
                                onNavigateHome = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("brigadista_mirim_details") {
                            _root_ide_package_.br.com.supportfire.app.ui.screens.BrigadistaMirimScreen(
                                onNavigateToRegistration = { courseName ->
                                    val encodedCourseName = URLEncoder.encode(
                                        courseName,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate("registration/$encodedCourseName")
                                },
                                onNavigateHome = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            "registration/{courseName}",
                            arguments = listOf(navArgument("courseName") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val encodedCourseName =
                                backStackEntry.arguments?.getString("courseName") ?: ""
                            val courseName = URLDecoder.decode(
                                encodedCourseName,
                                StandardCharsets.UTF_8.toString()
                            )

                            _root_ide_package_.br.com.supportfire.app.ui.screens.RegistrationScreen(
                                selectedCourse = courseName,
                                onRegistrationSuccess = { registrationCode ->
                                    navController.navigate("success/$registrationCode") {
                                        popUpTo("home")
                                    }
                                },
                                onNavigateHome = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            "success/{registrationCode}",
                            arguments = listOf(navArgument("registrationCode") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val code = backStackEntry.arguments?.getString("registrationCode")
                            _root_ide_package_.br.com.supportfire.app.ui.screens.RegistrationSuccessScreen(
                                registrationCode = code,
                                onGoToHome = {
                                    navController.navigate("home") {
                                        popUpTo("home") {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
