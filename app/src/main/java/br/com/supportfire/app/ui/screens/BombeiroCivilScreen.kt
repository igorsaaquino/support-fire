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
import com.supportfire.supportfire.ui.theme.Orange800

@Composable
fun BombeiroCivilScreen(onNavigateToRegistration: (String) -> Unit, onNavigateHome: () -> Unit) {
    val courseName = "Bombeiro Profissional Civil"
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
                text = "O que é  Bombeiro Profissional Civil (BPC)?\n" +
                        "\n" +
                        "Considera-se Bombeiro Civil aquele que, habilitado , exerça, em caráter habitual, função remunerada e exclusiva de prevenção e combate a incêndio, como empregado contratado diretamente por empresas privadas ou públicas, sociedades de economia mista, ou empresas especializadas em prestação de serviços de prevenção e combate a  incêndio.\n" +
                        "\n" +
                        "DE ACORDO COM A LEI FEDERAL DE 11.901 DE 12 DE JANEIRO DE 2009 – A jornada do Bombeiro Civil é de 12 (doze) horas de trabalho por 36 (trinta e seis) horas de descanso, num total de 36 (trinta e seis) horas semanais.\n" +
                        "\n" +
                        "É assegurado ao Bombeiro Civil:\n" +
                        "\n" +
                        "I – uniforme especial a expensas do empregador;\n" +
                        "II – seguro de vida em grupo, estipulado pelo empregador;\n" +
                        "III – adicional de periculosidade de 30% (trinta por cento) do salário mensal sem os acréscimos resultantes de gratificações, prêmios ou participações nos lucros da empresa;\n" +
                        "\n" +
                        "O nosso Curso é PROFISSIONALIZANTE e cumpre os padrões exigidos na Resolução SEDEC nº 31 de 10 de janeiro de 2013.  Confira aqui no site do Corpo de Bombeiros a relação de empresas credenciadas.\n" +
                        "\n" +
                        "O curso é composto por aulas teóricas, práticas e simulados. As aulas teóricas ocorrem em ambientes de aprendizado climatizados com modernos recursos áudio visuais. Para a prática contamos com um avançado campo de treinamento com instalações modernas e criadas especialmente para proporcionar um treinamento de qualidade e realístico.\n" +
                        "\n" +
                        "CONTEÚDO DO CURSO:\n" +
                        "PREVENÇÃO E COMBATE A INCÊNDIO;\n" +
                        "EQUIPAMENTO DE COMBATE A INCÊNDIOS;\n" +
                        "EQUIPAMENTO DE PROTEÇÃO INDIVIDUAL e COLETIVA;\n" +
                        "TÉCNICAS DE ESCAPE E DE ABANDONO DE ÁREA\n" +
                        "ATENDIMENTO PRÉ-HOSPITALAR;\n" +
                        "SALVAMENTO EM ESPAÇO CONFINADO;\n" +
                        "RESGATE EM ALTURA;\n" +
                        "EMERGÊNCIA COM PRODUTOS PERIGOSOS;\n" +
                        "CONHECER SOLUÇÕES E MEDIDAS ESTRUTURAIS E NÃO ESTRUTURAIS PARA A PROTEÇÃO DO PATRIMONIO;\n" +
                        "SALVAMENTO TERRESTRE;\n" +
                        "ENTRE OUTROS\n" +
                        "\n" +
                        "CARGA HORÁRIA / TURMAS:\n" +
                        "80 HORAS;\n" +
                        "Segunda, quarta e sexta das 18 às 22:00 horas e aos Sábados das 08:00 ás 17:00.\n" +
                        "\n" +
                        "PRÉ REQUISITOS:\n" +
                        "SER BRASILEIRO OU ESTRANGEIRO COM SITUAÇÃO REGULAR NO BRASIL;\n" +
                        "TER IDADE MÍNIMA DE 18 ANOS;\n" +
                        "TER CURSADO PELO MENOS O QUINTO ANO DO ENSINO FUNDAMENTAL;\n" +
                        "ESTAR EM DIA COM SUAS OBRIGAÇÕES ELEITORAIS E MILITARES;\n" +
                        "POSSUIR CADASTRO DE PESSOA FÍSICA;\n" +
                        "APRESENTAR ATESTADO MÉDICO\n" +
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
