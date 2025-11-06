package br.com.supportfire.app.ui


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import br.com.supportfire.app.R
import br.com.supportfire.app.ui.theme.Orange800
// CORREÇÃO: Import corrigido para o pacote correto do seu projeto
import br.com.supportfire.app.ui.theme.SupportFireTheme

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

        // 2. Três Botões para os Cursos (com ícones)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaçamento entre os botões
        ) {
            // ATUALIZAÇÃO: Passando o ícone para cada botão.
            // **LEMBRE-SE:** Você precisa ter os ícones 'ic_fireman', 'ic_medical', 'ic_child' na sua pasta res/drawable.
            CourseButton(
                text = "Bombeiro Profissional Civil",
                iconRes = R.drawable.ic_bombeiro_personalizado, // Exemplo de ícone
                onClick = { onNavigateToCourseDetails("bombeiro_civil_details") }
            )

            CourseButton(
                text = "Socorrista / BLS",
                iconRes = R.drawable.ic_socorrista_personalizado, // Exemplo de ícone
                onClick = { onNavigateToCourseDetails("socorrista_details") }
            )

            CourseButton(
                text = "Brigadista Mirim",
                iconRes = R.drawable.ic_mirim_personalizado_png, // Exemplo de ícone
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
 * Um Composable reutilizável para os botões de curso, agora com um ícone na frente do texto.
 */
@Composable
private fun CourseButton(text: String, @DrawableRes iconRes: Int, onClick: () -> Unit) {
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
        // Usamos uma Row para alinhar o ícone e o texto horizontalmente
        Row(
            verticalAlignment = Alignment.CenterVertically,
            // Não precisa de Arrangement.Center aqui, pois o Button já centraliza o conteúdo
        ) {
            // Ícone que vem na frente do texto
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null, // O texto do botão já descreve a ação
                modifier = Modifier.size(24.dp)
            )

            // Espaço entre o ícone e o texto
            Spacer(modifier = Modifier.width(12.dp))

            // Texto do botão
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SupportFireTheme {
        HomeScreen(onNavigateToCourseDetails = {})
    }
}
