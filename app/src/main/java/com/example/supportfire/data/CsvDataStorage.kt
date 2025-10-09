package com.example.supportfire.data

import android.content.Context
import com.example.supportfire.model.Registration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class CsvDataStorage(private val context: Context) {

    suspend fun saveRegistration(data: Registration) {
        withContext(Dispatchers.IO) {
            val file = File(context.filesDir, "registrations.csv")
            val isNewFile = !file.exists()

            FileOutputStream(file, true).bufferedWriter().use { writer ->
                if (isNewFile) {
                    // Escreve o cabeçalho se o arquivo for novo
                    writer.write(
                        "Nome,E-mail,Telefone,Sexo,Tipo Sanguíneo,Endereço," +
                                "Bairro,Cidade,Estado,Data de Nascimento,Nome do Pai," +
                                "Nome da Mãe,Curso Desejado,Como Soube\n"
                    )
                }
                // Escreve os dados do novo registro
                writer.write(
                    "${data.name},${data.email},${data.phone},${data.gender}," +
                            "${data.bloodType},${data.address},${data.neighborhood}," +
                            "${data.city},${data.state},${data.birthDate},${data.fatherName}," +
                            "${data.motherName},${data.desiredCourse},${data.discoverySource}\n"
                )
            }
        }
    }
}
