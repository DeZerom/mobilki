package com.example.mobilki.presentation.screens.auth_screen.login

data class LoginScreenState(
    val code: String = "",
    val phone: String = "",
    val pass: String = "",
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val successful: Boolean = false,
    val userId: Int = 0
)
