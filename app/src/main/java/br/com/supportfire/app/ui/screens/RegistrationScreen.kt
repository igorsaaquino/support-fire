package br.com.supportfire.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.supportfire.app.data.SheetApiClient
import br.com.supportfire.app.data.model.RegistrationData
import br.com.supportfire.app.ui.theme.Orange800
import br.com.supportfire.app.ui.theme.SupportFireTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    selectedCourse: String,
    onRegistrationSuccess: (String) -> Unit,
    onNavigateHome: () -> Unit
) {
    // --- ESTADOS DO FORMULÁRIO ---
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

    // --- ESTADOS DA UI E LÓGICA ---
    var isLoading by remember { mutableStateOf(false) }

    // NOVO: Estado para armazenar o código de registro quando a API retorna com sucesso.
    var registrationCodeResult by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val apiClient = remember { SheetApiClient() } // Instância única, como corrigido anteriormente

    // --- VALIDAÇÃO DO FORMULÁRIO ---
    val isFormValid by remember(
        name,
        email,
        phone,
        gender,
        bloodType,
        address,
        neighborhood,
        city,
        state,
        birthDate,
        fatherName,
        motherName,
        howDidYouHear
    ) {
        derivedStateOf {
            name.isNotBlank() && email.contains("@") && phone.length >= 10 &&
                    gender.isNotBlank() && bloodType.isNotBlank() && address.isNotBlank() &&
                    neighborhood.isNotBlank() && city.isNotBlank() && state.isNotBlank() &&
                    birthDate.isNotBlank() && fatherName.isNotBlank() && motherName.isNotBlank() &&
                    howDidYouHear.isNotBlank()
        }
    }

    // --- EFEITO COLATERAL PARA NAVEGAÇÃO ---
    // Este `LaunchedEffect` observa a variável `registrationCodeResult`.
    // Quando ela mudar de `null` para um valor (o código), ele executará o bloco.
    // O `Unit` como chave significa que este efeito só será lançado uma vez quando o Composable entrar na tela.
    LaunchedEffect(registrationCodeResult) {
        registrationCodeResult?.let { code ->
            // Se o código não for nulo, navegue para a tela de sucesso.
            onRegistrationSuccess(code)
        }
    }

    val backgroundBrush = Brush.verticalGradient(colors = listOf(Orange800, Color(0xFFD32F2F)))

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundBrush)
                .statusBarsPadding()
        ) {
            IconButton(onClick = onNavigateHome) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
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
                    text = "Formulário de Pré-Inscrição",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

                // --- CAMPOS DE TEXTO ---
                CustomOutlinedTextField(
                    value = selectedCourse,
                    label = "Curso Desejado",
                    readOnly = true
                )
                CustomOutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Nome Completo *",
                    capitalization = KeyboardCapitalization.Words
                )
                CustomOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "E-mail *",
                    keyboardType = KeyboardType.Email
                )
                CustomOutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = "Telefone (com DDD) *",
                    keyboardType = KeyboardType.Phone
                )
                DropdownTextField(
                    label = "Sexo *",
                    options = listOf("Masculino", "Feminino"),
                    selectedValue = gender,
                    onValueChange = { gender = it })
                DropdownTextField(
                    label = "Tipo Sanguíneo *",
                    options = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"),
                    selectedValue = bloodType,
                    onValueChange = { bloodType = it })
                CustomOutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = "Endereço Completo (Rua, N°) *",
                    capitalization = KeyboardCapitalization.Words
                )
                CustomOutlinedTextField(
                    value = neighborhood,
                    onValueChange = { neighborhood = it },
                    label = "Bairro *",
                    capitalization = KeyboardCapitalization.Words
                )
                CustomOutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = "Cidade *",
                    capitalization = KeyboardCapitalization.Words
                )
                CustomOutlinedTextField(
                    value = state,
                    onValueChange = { state = it },
                    label = "Estado *",
                    capitalization = KeyboardCapitalization.Characters
                )
                CustomOutlinedTextField(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    label = "Data de Nascimento (DD/MM/AAAA) *",
                    keyboardType = KeyboardType.Number
                )
                CustomOutlinedTextField(
                    value = fatherName,
                    onValueChange = { fatherName = it },
                    label = "Nome do Pai *",
                    capitalization = KeyboardCapitalization.Words
                )
                CustomOutlinedTextField(
                    value = motherName,
                    onValueChange = { motherName = it },
                    label = "Nome da Mãe *",
                    capitalization = KeyboardCapitalization.Words
                )
                CustomOutlinedTextField(
                    value = howDidYouHear,
                    onValueChange = { howDidYouHear = it },
                    label = "Como soube do curso? *",
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Done
                )

                Spacer(modifier = Modifier.height(32.dp))

                // --- BOTÃO DE ENVIO ---
                Button(
                    onClick = {
                        val registrationData = RegistrationData(
                            course = selectedCourse,
                            name = name,
                            email = email,
                            phone = phone,
                            gender = gender,
                            bloodType = bloodType,
                            birthDate = birthDate,
                            address = address,
                            neighborhood = neighborhood,
                            city = city,
                            state = state,
                            fatherName = fatherName,
                            motherName = motherName,
                            howDidYouHear = howDidYouHear
                        )

                        coroutineScope.launch {
                            isLoading = true
                            val result =
                                apiClient.submitRegistration(registrationData) // Executa a chamada
                            isLoading = false

                            if (result != null) {
                                // SUCESSO: Atualiza o estado com o código recebido.
                                // O `LaunchedEffect` vai detectar essa mudança e acionar a navegação.
                                Toast.makeText(
                                    context,
                                    "Inscrição enviada com sucesso!",
                                    Toast.LENGTH_LONG
                                ).show()
                                registrationCodeResult = result
                            } else {
                                // FALHA: Mostra um erro.
                                Toast.makeText(
                                    context,
                                    "Erro ao enviar inscrição. Verifique sua conexão e tente novamente.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
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
                    enabled = isFormValid && !isLoading
                ) {
                    Text(text = "Enviar Inscrição", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                strokeWidth = 4.dp
            )
        }
    }
}


// --- COMPONENTES REUTILIZÁVEIS E PREVIEW (permanecem iguais) ---

@Composable
private fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit = {}, // Provide a default empty lambda
    label: String,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        singleLine = true,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = capitalization,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Black.copy(alpha = 0.3f),
            unfocusedContainerColor = Color.Black.copy(alpha = 0.3f),
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.7f),
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownTextField(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {}, // Input is read-only, selection happens in the menu
            readOnly = true,
            label = { Text(label, color = Color.White) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                // The file content was cut off here, but assuming it continues...
                focusedContainerColor = Color.Black.copy(alpha = 0.3f),
                unfocusedContainerColor = Color.Black.copy(alpha = 0.3f),
                cursorColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White.copy(alpha = 0.7f)
            ),
            modifier = Modifier.menuAnchor() // Add menuAnchor modifier
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onValueChange(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    SupportFireTheme {
        RegistrationScreen(
            selectedCourse = "Bombeiro Civil",
            onRegistrationSuccess = {},
            onNavigateHome = {}
        )
    }
}