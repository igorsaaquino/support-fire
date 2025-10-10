package br.com.supportfire.app.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.supportfire.app.ui.theme.Orange800


@Composable
fun RegistrationSuccessScreen(
    registrationCode: String?,
    onGoToHome: () -> Unit
) {
    val context = LocalContext.current
    val code = registrationCode ?: "CÓDIGO_INVÁLIDO"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Inscrição Realizada com Sucesso!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Orange800,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Seu código de verificação é:",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = code,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Uma cópia com os detalhes da sua inscrição foi enviada para o seu e-mail.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botão para dúvidas no WhatsApp
        Button(
            onClick = {
                openWhatsApp(context, code)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)), // Cor do WhatsApp
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Tirar Dúvidas no WhatsApp", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para voltar à tela inicial
        OutlinedButton(
            onClick = onGoToHome,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Voltar ao Início")
        }
    }
}

// Função para abrir o WhatsApp
private fun openWhatsApp(context: Context, registrationCode: String) {
    val phoneNumber =
        "5521988232616" // SUBSTITUA PELO SEU NÚMERO DE WHATSAPP com código do país e DDD
    val message = "Olá, tenho uma dúvida sobre a minha inscrição. Meu código é $registrationCode."

    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(
                "https://api.whatsapp.com/send?phone=$phoneNumber&text=${
                    Uri.encode(message)
                }"
            )
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "WhatsApp não instalado.", Toast.LENGTH_SHORT).show()
    }
}