package com.example.supportfire.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun BrigadistaMirimScreen(onNavigateToRegistration: (String) -> Unit) {
    val courseName = "Brigadista Mirim"

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Orange800, Color(0xFFD32F2F))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
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
            text = "Fale sobre o projeto social do Brigadista Mirim. Descreva as atividades lúdicas e educativas, os valores de cidadania, prevenção de acidentes domésticos e respeito ao meio ambiente que são ensinados às crianças.",
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