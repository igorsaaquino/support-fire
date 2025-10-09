package com.example.supportfire.ui

import android.content.Context
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supportfire.data.CsvDataStorage
import com.example.supportfire.model.Registration
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(onRegistrationSuccess: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
    var desiredCourse by remember { mutableStateOf("") }
    var discoverySource by remember { mutableStateOf("") }
    var hasError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Formulário de Pré-Inscrição") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (hasError) {
                Text(
                    "Todos os campos são obrigatórios.",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            // Campos do formulário...
            CustomOutlinedTextField(value = name, onValueChange = { name = it }, label = "Nome Completo")
            CustomOutlinedTextField(value = email, onValueChange = { email = it }, label = "E-mail", keyboardType = KeyboardType.Email)
            CustomOutlinedTextField(value = phone, onValueChange = { phone = it }, label = "Telefone", keyboardType = KeyboardType.Phone)
            CustomOutlinedTextField(value = gender, onValueChange = { gender = it }, label = "Sexo")
            CustomOutlinedTextField(value = bloodType, onValueChange = { bloodType = it }, label = "Tipo Sanguíneo")
            CustomOutlinedTextField(value = address, onValueChange = { address = it }, label = "Endereço (Rua, Nº)")
            CustomOutlinedTextField(value = neighborhood, onValueChange = { neighborhood = it }, label = "Bairro")
            CustomOutlinedTextField(value = city, onValueChange = { city = it }, label = "Cidade")
            CustomOutlinedTextField(value = state, onValueChange = { state = it }, label = "Estado")
            CustomOutlinedTextField(value = birthDate, onValueChange = { birthDate = it }, label = "Data de Nascimento (DD/MM/AAAA)")
            CustomOutlinedTextField(value = fatherName, onValueChange = { fatherName = it }, label = "Nome do Pai")
            CustomOutlinedTextField(value = motherName, onValueChange = { motherName = it }, label = "Nome da Mãe")
            CustomOutlinedTextField(value = desiredCourse, onValueChange = { desiredCourse = it }, label = "Curso Desejado")
            CustomOutlinedTextField(value = discoverySource, onValueChange = { discoverySource = it }, label = "Como soube do curso?", imeAction = ImeAction.Done)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val isFormValid = listOf(name, email, phone, gender, bloodType, address, neighborhood, city, state, birthDate, fatherName, motherName, desiredCourse, discoverySource).all { it.isNotBlank() }

                    if (isFormValid) {
                        hasError = false
                        // Correção
                        val registrationData = Registration(name, email, phone, gender, bloodType, address, neighborhood, city, state, birthDate, fatherName, motherName, desiredCourse, discoverySource)

                        scope.launch {
                            saveRegistration(context, registrationData)
                            Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
                            onRegistrationSuccess()
                        }
                    } else {
                        hasError = true
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Enviar", fontSize = 18.sp)
            }
        }
    }
}

private suspend fun saveRegistration(context: Context, data: `Registration`) {
    val storage = CsvDataStorage(context)
    storage.saveRegistration(data)
}

@Composable
private fun CustomOutlinedTextField(value: String, onValueChange: (String) -> Unit, label: String, keyboardType: KeyboardType = KeyboardType.Text, imeAction: ImeAction = ImeAction.Next) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction)
    )
}
