package br.com.supportfire.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.supportfire.app.ui.HomeScreen
import br.com.supportfire.app.ui.theme.SupportFireTheme
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            // This should now correctly resolve to the imported theme
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
                            br.com.supportfire.app.ui.screens.BombeiroCivilScreen( // Corrected fully qualified name
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
                            br.com.supportfire.app.ui.screens.SocorristaScreen( // Corrected fully qualified name
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
                            br.com.supportfire.app.ui.screens.BrigadistaMirimScreen( // Corrected fully qualified name
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

                            br.com.supportfire.app.ui.screens.RegistrationScreen( // Corrected fully qualified name
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
                            br.com.supportfire.app.ui.screens.RegistrationSuccessScreen( // Corrected fully qualified name
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
