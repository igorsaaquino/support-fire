package com.example.supportfire.ui // Your original package

import android.widget.Space
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supportfire.R
import com.example.supportfire.ui.theme.Orange800

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
            text = "Brigadista Mirim",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(id = R.drawable.slider_2),
            "Logotipo do Support Fire",
            modifier = Modifier.size(900.dp, 300.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

//        Text(
//            text = "Formando jovens heróis desde cedo. Aprenda sobre primeiros socorros, combate a incêndios, meio ambiente e cidadania.",
//            fontSize = 18.sp,
//            color = Color.White,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//
//        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "A Support Fire propôs a criação do “Curso de Brigadista Mirim” como um instrumento, atuando, especialmente, com foco na promoção da qualidade de vida, prevenção da criminalidade e da violência, através de um conjunto estruturado de políticas públicas voltadas para a inclusão social, integração e mobilização comunitária. Esse conjunto de ações tem como eixos principais a defesa da vida, o respeito à cidadania e a garantia dos direitos fundamentais da criança e do adolescente.No curso, os alunos aprenderão noções nas atividades de Defesa Civil, Primeiros Socorros, Combate a Incêndio, Oceanografia, Preservação do Meio Ambiente, Doenças Sexualmente Transmissíveis, Drogas e seus Malefícios, Profissões, Acidentes Domésticos, Acidentes Automobilísticos, Animais Peçonhentos e Acionamento dos Órgãos Públicos nos Eventos Adversos.",
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
    