package com.example.supportfire

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.supportfire.ui.screens.RegistrationScreen
import com.example.supportfire.ui.screens.RegistrationSuccessScreen
import com.example.supportfire.ui.HomeScreen
import com.example.supportfire.ui.theme.SupportFireTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupportFireTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(onNavigateToRegistration = {
                                navController.navigate("registration")
                            })
                        }
                        composable("registration") {
                            RegistrationScreen(
                                onRegistrationSuccess = { registrationCode ->
                                    // Navega para a tela de sucesso passando o código
                                    navController.navigate("success/$registrationCode") {
                                        // Limpa a pilha de navegação para que o usuário não volte para o formulário
                                        popUpTo("home")
                                    }
                                }
                            )
                        }
                        // --- NOVA ROTA PARA A TELA DE SUCESSO ---
                        composable(
                            route = "success/{registrationCode}", // A rota aceita um argumento
                            arguments = listOf(navArgument("registrationCode") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val code = backStackEntry.arguments?.getString("registrationCode")
                            RegistrationSuccessScreen(
                                registrationCode = code,
                                onGoToHome = {
                                    navController.navigate("home") {
                                        popUpTo("home") { inclusive = true }
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
