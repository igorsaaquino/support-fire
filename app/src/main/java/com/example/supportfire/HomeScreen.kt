package com.example.supportfire.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.supportfire.R // Certifique-se de que o import para R está correto
import com.example.supportfire.ui.theme.Orange800
import com.example.supportfire.ui.theme.SupportFireTheme

@Composable
fun HomeScreen(onNavigateToCourseDetails: (String) -> Unit) {
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
            // ATENÇÃO: Verifique se 'support_fire_logo' é o nome correto da sua imagem na pasta res/drawable
            painter = painterResource(id = R.drawable.logo),
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
            // CORREÇÃO: Cada botão agora passa o nome do curso correto
            CourseButton(
                text = "Bombeiro Profissional Civil",
                onClick = { onNavigateToCourseDetails("bombeiro_civil_details") }
            )

            CourseButton(
                text = "Socorrista / BLS",
                onClick = { onNavigateToCourseDetails("socorrista_details") }
            )

            CourseButton(
                text = "Brigadista Mirim",
                onClick = { onNavigateToCourseDetails("brigadista_mirim_details") }
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SupportFireTheme {
        HomeScreen(onNavigateToCourseDetails = {})
    }
}
