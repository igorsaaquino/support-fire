package com.example.supportfire.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supportfire.data.CsvDataStorage
import com.example.supportfire.model.Registration
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(onRegistrationSuccess: (String) -> Unit) {
    // Gerenciadores de estado para cada campo do formulário
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    // Adicione outros campos conforme necessário

    var hasError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Torna a coluna rolável
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Formulário de Pré-Inscrição",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (hasError) {
            Text(
                "Por favor, preencha todos os campos.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Campos de Texto para o formulário
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome Completo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Telefone (com DDD)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("Data de Nascimento (DD/MM/AAAA)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Endereço (Rua, Nº, Bairro)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Cidade") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state,
            onValueChange = { state = it },
            label = { Text("Estado") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        // Adicione mais TextFields para os outros campos aqui

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Validação simples
                val isFormValid = name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() &&
                        birthDate.isNotBlank() && address.isNotBlank() && city.isNotBlank() && state.isNotBlank()

                if (isFormValid) {
                    hasError = false
                    val registrationCode = generateUniqueCode()

                    // Criando o objeto de registro
                    val registrationData = Registration(
                        name = name, email = email, phone = phone, gender = "",
                        bloodType = "", address = address, neighborhood = "", city = city,
                        state = state, birthDate = birthDate, fatherName = "", motherName = "",
                        desiredCourse = "", discoverySource = ""
                    )

                    scope.launch {
                        try {
                            // 1. Salva os dados no arquivo
                            saveRegistration(context, registrationData)

                            // 2. CHAMA A FUNÇÃO PARA ENVIAR O E-MAIL
                            sendConfirmationEmail(context, registrationData, registrationCode)

                            // 3. Mostra feedback e navega
                            Toast.makeText(context, "Cadastro enviado!", Toast.LENGTH_SHORT).show()
                            onRegistrationSuccess(registrationCode)

                        } catch (e: Exception) {
                            // Lida com o erro aqui, onde 'context' está acessível
                            Toast.makeText(context, "Erro ao salvar os dados.", Toast.LENGTH_LONG).show()
                            Log.e("RegistrationScreen", "Falha ao salvar o cadastro", e)
                        }
                    }
                } else {
                    hasError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Completar Pré-Inscrição", fontSize = 18.sp)
        }
    }
}

private fun generateUniqueCode(): String {
    return UUID.randomUUID().toString().substring(0, 8).uppercase()
}

private suspend fun saveRegistration(context: Context, data: Registration) {
    val storage = CsvDataStorage(context)
    storage.saveRegistration(data)
}

// <<< --- ADICIONE ESTA FUNÇÃO --- >>>
private fun sendConfirmationEmail(context: Context, data: Registration, code: String) {
    val ourEmail = "seu-email-de-contato@exemplo.com" // <-- SUBSTITUA PELO SEU E-MAIL
    val subject = "Confirmação de Inscrição - Support Fire"
    val body = """
        Olá, ${data.name}!

        Sua pré-inscrição no projeto Support Fire foi realizada com sucesso.
        Seu código de verificação é: $code

        Detalhes da Inscrição:
        - Nome: ${data.name}
        - E-mail: ${data.email}
        - Telefone: ${data.phone}

        Em breve, entraremos em contato com mais informações.

        Atenciosamente,
        Equipe Support Fire
    """.trimIndent()

    try {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            // Usar "mailto:" garante que apenas apps de e-mail sejam abertos
            this.data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(data.email)) // E-mail do usuário
            putExtra(Intent.EXTRA_CC, arrayOf(ourEmail))      // Cópia para o seu e-mail
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        // Inicia um seletor para que o usuário escolha seu app de e-mail
        context.startActivity(Intent.createChooser(emailIntent, "Enviar E-mail de Confirmação..."))

    } catch (ex: android.content.ActivityNotFoundException) {
        // Caso o usuário não tenha nenhum app de e-mail instalado
        Toast.makeText(context, "Não há aplicativos de e-mail instalados.", Toast.LENGTH_SHORT).show()
    }
}
