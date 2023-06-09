package com.example.mobilki.presentation.screens.greetings_sreen

import com.example.mobilki.data.models.UserModel

data class GreetingsScreenState(
    val user: UserModel? = null,
    val isLoggedOut: Boolean = false,
    val usersList: List<UserModel> = emptyList(),
    val navigateToWeather: Boolean = false
)
