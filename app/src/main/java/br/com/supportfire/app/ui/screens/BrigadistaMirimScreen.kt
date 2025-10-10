package br.com.supportfire.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import br.com.supportfire.app.ui.theme.Orange800

@Composable
fun BrigadistaMirimScreen(onNavigateToRegistration: (String) -> Unit, onNavigateHome: () -> Unit) {
    val courseName = "Brigadista Mirim"
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
                text = "Curso de Brigadista Mirim\n" +
                        " \n" +
                        "\n" +
                        "A MP Fire propôs a criação do “Curso de Brigadista Mirim” como um instrumento, atuando, especialmente, com foco na promoção da qualidade de vida, prevenção da criminalidade e da violência, através de um conjunto estruturado de políticas públicas voltadas para a inclusão social, integração e mobilização comunitária.\n" +
                        " Esse conjunto de ações tem como eixos principais a defesa da vida, o respeito à cidadania e a garantia dos direitos fundamentais da criança e do adolescente.\n" +
                        "No curso, os alunos aprenderão noções nas atividades de Defesa Civil, Primeiros Socorros, Combate a Incêndio, Oceanografia, Preservação do Meio Ambiente, Doenças Sexualmente Transmissíveis, Drogas e seus Malefícios, Profissões, Acidentes Domésticos, Acidentes Automobilísticos, Animais Peçonhentos e Acionamento dos Órgãos Públicos nos Eventos Adversos.\n" +
                        " \n" +
                        "\n",
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
