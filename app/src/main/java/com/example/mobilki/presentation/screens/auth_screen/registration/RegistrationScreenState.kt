package com.example.mobilki.presentation.screens.auth_screen.registration

data class RegistrationScreenState(
    val code: String = "",
    val number: String = "",
    val name: String = "",
    val pass: String = "",
    val confirmPass: String = "",
    val isLoading: Boolean = false
)
