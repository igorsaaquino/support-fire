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
                text = "Certificação Internacional - 48 horas/aula\n" +
                        " \n" +
                        "\n" +
                        "A Carreira de Socorrista de Emergência\n" +
                        "\n" +
                        " Ser um Socorrista de Emergência é ser um profissional da área médica habilitado e treinado para o socorro pré-hospitalar e o atendimento médico em locais fora do ambiente hospitalar. Essa categoria profissional é formada por socorristas, técnicos em emergências médicas. \n" +
                        "\n" +
                        "Qualidade Necessária:\n" +
                        "* Compaixão - Vontade de ajudar uma pessoa no momento em que ela mais precisa de ajuda.\n" +
                        "* Habilidades interpessoais - Ser capaz de trabalhar em equipe.\n" +
                        "* Disposição para cumprir ações orientadas.\n" +
                        "* Ser capaz de garantir sua segurança pessoal e a das vítimas no local de atendimento.\n" +
                        "* Conhecer as técnicas de transporte politraumatizado.\n" +
                        "* Ser capaz de se comunicar via rádio com seu coordenador médico, para informar a correta situação da vítima e da cena do acidente.\n" +
                        "\n" +
                        "A participação no curso de Atendimento Pré-Hospitalar, também pode ser conhecido como curso de Primeiros Socorros ou ainda como curso de resgate, é permitida a qualquer pessoa que busca capacitação a fim de conseguir identificar situações de emergências e realizar técnicas de Primeiros Socorros e Atendimento Pré-Hospitalar de forma à aumentar as chances de sobrevida de uma vítima até que o socorro especializado chegue ao local ou até a efetiva entrega dessa vítima no hospital.\n" +
                        "\n" +
                        "Seja você também um Socorrista de Emergência\n" +
                        "Pensando em amenizar o número de mortes causados por variados tipos de acidentes, a MP FIRE, viu a grande necessidade de implantar um curso com foco Pré Hospitalar. O Curso de Socorrista tem como propósito conscientizar os inexperientes e aperfeiçoar os profissionais da área sobre a importância do aprendizado, treinamento e prevenção contra os acidentes. O curso é  composto por aulas teóricas, práticas e bastante simulados para o aluno aprender o mais próximo da realidade.\n" +
                        "Ser um Socorrista é  ser um profissional habilitado para o socorro pré-hospitalar e atendimento fora do ambiente hospitalar. Cabe ao profissional, controlar o local do acidente de modo a proteger a si mesmo, ao paciente e prevenir que outros acidentes possam acontecer. Além disso, notificar a emergência, permitir a chegada dos médicos com mais agilidade e já cientes da ocorrência, do estado do paciente e todas as informações necessárias para que o atendimento seja rápido e eficaz.\n" +
                        "\n" +
                        "GRADE CURRICULAR DO CURSO - PROGRAMA DE 40 HORAS/AULA - TEÓRICA E PRÁTICA:\n" +
                        "\n" +
                        "Legislação específica;\n" +
                        "Procedimentos iniciais;\n" +
                        "Avaliação inicial;\n" +
                        "Parada cardíaca súbita;\n" +
                        "Ressuscitação cardiopulmonar (RCP);\n" +
                        "Desfibrilação precoce;\n" +
                        "Corrente da sobrevivência;\n" +
                        "Parada cardíaca secundária;\n" +
                        "Doenças cardíacas;\n" +
                        "Operador de RCP e DEA;\n" +
                        "Reconhecendo uma emergência;\n" +
                        "Segurança pessoal e decisão de ajudar;\n" +
                        "Protegendo a sim mesmo;\n" +
                        "O serviço médico de emergência;\n" +
                        "Compressões torácicas em adulto;\n" +
                        "Compressão torácica em crianças e bebes;\n" +
                        "Ventilação de resgate;\n" +
                        "Estabelecendo uma via aérea;\n" +
                        "Usando dispositivo de barreira;\n" +
                        "Fornecendo ventilações;\n" +
                        "Desfibrilador Externo Automático – DEA;\n" +
                        "Operações com o DEA;\n" +
                        "Solução de problemas e considerações sobre o DEA;\n" +
                        "Usando o DEA em adulto;\n" +
                        "Usando o DEA em crianças e bebes;\n" +
                        "Avaliação primária – vitima sem reação;\n" +
                        "Posição de recuperação;\n" +
                        "Cuidando de um ataque cardíaco – Adulto;\n" +
                        "Cuidando de um ataque cardíaco – Criança;\n" +
                        "Cuidando de um ataque cardíaco – bebês;\n" +
                        "RCP feito por mais de uma pessoa;\n" +
                        "OVACE - Obstrução das vias aéreas por corpos estranhos;\n" +
                        "Atendendo a vítima de OVACE;\n" +
                        "Hemorragias e métodos hemostáticos;\n" +
                        "Estado de choque;\n" +
                        "Lesões musculoesqueléticas (fraturas, entorse e luxação) e técnicas de imobilização;\n" +
                        "Ferimentos;\n" +
                        "Queimaduras;\n" +
                        "Emergência clinica;\n" +
                        "Movimentação, remoção e transporte de vítima;\n" +
                        "Pessoas com mobilidade reduzida;\n" +
                        "Protocolo com incidente com múltiplas vítimas;\n" +
                        "Simulado (múltiplas vitimas, resgate em local de difícil acesso); e \n" +
                        "Psicologia em emergência.\n" +
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
