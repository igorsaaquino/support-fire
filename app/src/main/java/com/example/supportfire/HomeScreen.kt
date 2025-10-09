package com.example.supportfire.ui // Your original package

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supportfire.ui.theme.Orange800 // <-- ADD THIS IMPORT

@Composable
fun HomeScreen(onNavigateToRegistration: () -> Unit) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Orange800, Color(0xFFD32F2F)) // Now this should work
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .padding(16.dp)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Support Fire",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Formando jovens heróis desde cedo. Aprenda sobre primeiros socorros, combate a incêndios, meio ambiente e cidadania.",
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onNavigateToRegistration,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Orange800 // And this
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
    }
}
    