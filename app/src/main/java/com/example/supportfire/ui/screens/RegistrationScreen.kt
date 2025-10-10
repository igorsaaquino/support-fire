package com.example.supportfire.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supportfire.ui.theme.Orange800
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    selectedCourse: String,
    onRegistrationSuccess: (String) -> Unit,
    onNavigateHome: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var bloodType by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var neighborhood by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var fatherName by remember { mutableStateOf("") }
    var motherName by remember { mutableStateOf("") }
    var howDidYouHear by remember { mutableStateOf("") }

    val isFormValid = name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() &&
            gender.isNotBlank() && bloodType.isNotBlank() && address.isNotBlank() &&
            neighborhood.isNotBlank() && city.isNotBlank() && state.isNotBlank() &&
            birthDate.isNotBlank() && fatherName.isNotBlank() && motherName.isNotBlank() &&
            howDidYouHear.isNotBlank()

    val context = LocalContext.current

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Orange800, Color(0xFFD32F2F))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .statusBarsPadding()
    ) {
        IconButton(onClick = onNavigateHome) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar para a Home",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pré-Inscrição",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomOutlinedTextField(
                value = selectedCourse,
                label = "Curso Desejado",
                readOnly = true
            )

            CustomOutlinedTextField(value = name, onValueChange = { name = it }, label = "Nome Completo *")
            CustomOutlinedTextField(value = email, onValueChange = { email = it }, label = "E-mail *", keyboardType = KeyboardType.Email)
            CustomOutlinedTextField(value = phone, onValueChange = { phone = it }, label = "Telefone *", keyboardType = KeyboardType.Phone)

            DropdownTextField(
                label = "Sexo *",
                options = listOf("Masculino", "Feminino"),
                selectedValue = gender,
                onValueChange = { gender = it }
            )

            DropdownTextField(
                label = "Tipo Sanguíneo *",
                options = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"),
                selectedValue = bloodType,
                onValueChange = { bloodType = it }
            )

            CustomOutlinedTextField(value = address, onValueChange = { address = it }, label = "Endereço *")
            CustomOutlinedTextField(value = neighborhood, onValueChange = { neighborhood = it }, label = "Bairro *")
            CustomOutlinedTextField(value = city, onValueChange = { city = it }, label = "Cidade *")
            CustomOutlinedTextField(value = state, onValueChange = { state = it }, label = "Estado *")
            CustomOutlinedTextField(value = birthDate, onValueChange = { birthDate = it }, label = "Data de Nascimento *", keyboardType = KeyboardType.Number)
            CustomOutlinedTextField(value = fatherName, onValueChange = { fatherName = it }, label = "Nome do Pai *")
            CustomOutlinedTextField(value = motherName, onValueChange = { motherName = it }, label = "Nome da Mãe *")
            CustomOutlinedTextField(value = howDidYouHear, onValueChange = { howDidYouHear = it }, label = "Como soube do curso? *")

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (isFormValid) {
                        val registrationCode = UUID.randomUUID().toString().substring(0, 8)

                        sendRegistrationEmail(
                            context = context,
                            adminRecipient = "igorsalencar1985@gmail.com",
                            userRecipient = email,
                            subject = "Nova Pré-Inscrição Recebida - Curso: $selectedCourse",
                            body = """
                                Uma nova pré-inscrição foi realizada pelo aplicativo.

                                Código de Inscrição: $registrationCode

                                --- DADOS DO CANDIDATO ---
                                Curso Desejado: $selectedCourse
                                Nome: $name
                                E-mail: $email
                                Telefone: $phone
                                Sexo: $gender
                                Tipo Sanguíneo: $bloodType
                                Data de Nascimento: $birthDate
                                Endereço: $address
                                Bairro: $neighborhood
                                Cidade: $city
                                Estado: $state
                                Nome do Pai: $fatherName
                                Nome da Mãe: $motherName
                                Como soube do curso?: $howDidYouHear
                            """.trimIndent()
                        )
                        onRegistrationSuccess(registrationCode)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Orange800
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 32.dp),
                enabled = isFormValid
            ) {
                Text(
                    text = "Enviar Inscrição",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

private fun sendRegistrationEmail(context: Context, adminRecipient: String, userRecipient: String, subject: String, body: String) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(adminRecipient))
        putExtra(Intent.EXTRA_CC, arrayOf(userRecipient))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    if (emailIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(emailIntent)
    }
}

@Composable
private fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false
) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            readOnly = readOnly,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = if (readOnly) Color.White.copy(alpha = 0.7f) else Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                disabledTextColor = Color.White.copy(alpha = 0.7f),
                disabledIndicatorColor = Color.White.copy(alpha = 0.5f),
                disabledLabelColor = Color.White.copy(alpha = 0.5f)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownTextField(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Spacer(modifier = Modifier.height(16.dp))
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                label = { Text(label) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    focusedTrailingIconColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.7f),
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                    unfocusedTextColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onValueChange(option)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}
