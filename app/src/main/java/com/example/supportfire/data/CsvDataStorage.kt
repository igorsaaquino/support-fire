package com.example.supportfire.data

import android.content.Context
import com.example.supportfire.model.Registration
import java.io.File
import java.io.FileWriter

class CsvDataStorage(private val context: Context) {
    fun saveRegistration(data: Registration) {
        val file = File(context.filesDir, "registrations.csv")
        val writer = FileWriter(file, true) // O 'true' anexa ao arquivo existente
        writer.use {
            // Adiciona cabeçalho se o arquivo for novo
            if (file.length() == 0L) {
                it.append("Name,Email,Phone,BirthDate,Address,City,State\n")
            }
            // Adiciona a nova linha de dados
            it.append("${data.name},${data.email},${data.phone},${data.birthDate},${data.address},${data.city},${data.state}\n")
        }
    }
}