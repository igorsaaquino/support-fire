package br.com.supportfire.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationData(
    val course: String,
    val name: String,
    val email: String,
    val phone: String,
    val gender: String,
    val bloodType: String,
    val birthDate: String,
    val address: String,
    val neighborhood: String,
    val city: String,
    val state: String,
    val fatherName: String,
    val motherName: String,
    val howDidYouHear: String
)