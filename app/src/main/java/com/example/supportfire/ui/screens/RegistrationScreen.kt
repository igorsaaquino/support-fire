package com.example.supportfire.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supportfire.R // Import para acessar os recursos (drawable)
import com.example.supportfire.ui.theme.Orange800
import com.example.supportfire.ui.theme.SupportFireTheme
import com.example.supportfire.model.Registration

@Composable
fun RegistrationScreen(
    selectedCourse: String, // Recebe o nome do curso
    onRegistrationSuccess: (String) -> Unit,
    SelectedCourse: String = "Brigadista Mirim"
) {
    // ...
    var desiredCourse by remember { mutableStateOf(selectedCourse) }
    // ...

//    // No objeto Registration que você cria, use a variável `desiredCourse`
//    val registrationData = Registration(
//        // ...
//        desiredCourse = desiredCourse,
//        // ...
//    )
}


@Composable
fun HomeScreen(onNavigateToRegistration: (String) -> Unit) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Orange800, Color(0xFFD32F2F)) // Degradê Laranja para Vermelho
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .padding(16.dp)
            .safeDrawingPadding() // Garante que o conteúdo não fique sob as barras do sistema
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Alinha os itens ao topo
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // 1. Logo Centralizada ao Topo
        Image(
            painter = painterResource(id = R.drawable.logo), // SUBSTITUA pelo nome do seu arquivo de logo
            contentDescription = "Logotipo do Support Fire",
            modifier = Modifier
                .size(180.dp) // Ajuste o tamanho conforme necessário
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Escolha o curso desejado:",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Três Botões para os Cursos
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaçamento entre os botões
        ) {
            CourseButton(
                text = "Bombeiro Profissional Civil",
                onClick = { onNavigateToRegistration("Bombeiro Civil") }
            )

            CourseButton(
                text = "Socorrista / BLS",
                onClick = { onNavigateToRegistration("Socorrista/BLS") }
            )

            CourseButton(
                text = "Brigadista Mirim",
                onClick = { onNavigateToRegistration("Brigadista Mirim") }
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Empurra o conteúdo abaixo para o final

        Text(
            text = "Formando heróis para a comunidade.",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.8f), // Cor branca com um pouco de transparência
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

/**
 * Um Composable reutilizável para os botões de curso, para manter o código limpo.
 */
@Composable
private fun CourseButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Orange800
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// --- Atualizando a Navegação ---
// A navegação agora precisa passar o nome do curso para a tela de registro.
// Você precisará atualizar os outros arquivos para lidar com isso.
// Por enquanto, vamos manter a Preview funcionando.

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SupportFireTheme {
        HomeScreen(onNavigateToRegistration = {})
    }
}
