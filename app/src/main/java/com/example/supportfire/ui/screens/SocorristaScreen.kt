package com.example.supportfire.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supportfire.ui.theme.Orange800

@Composable
fun SocorristaScreen(onNavigateToRegistration: (String) -> Unit, onNavigateHome: () -> Unit) {
    val courseName = "Socorrista / BLS"
    val backgroundBrush = Brush.verticalGradient(colors = listOf(Orange800, Color(0xFFD32F2F)))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .statusBarsPadding()
    ) {
        IconButton(onClick = onNavigateHome) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar para Home",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = courseName,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Descrição completa sobre o curso de Socorrista e Basic Life Support (BLS). Explique as técnicas de primeiro socorros, RCP, uso do DEA e outras habilidades vitais que serão ensinadas.",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onNavigateToRegistration(courseName) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Orange800
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "Fazer Pré-Inscrição",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
