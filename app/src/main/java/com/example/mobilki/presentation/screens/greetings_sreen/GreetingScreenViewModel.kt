package com.example.mobilki.presentation.screens.greetings_sreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.mobilki.R
import com.example.mobilki.data.repository.UserRepository
import com.example.mobilki.presentation.base.BaseViewModel
import com.example.mobilki.presentation.nav.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GreetingScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val userId: Int = savedStateHandle[NavRoutes.GREETINGS.argName] ?: 0

    private val _state = MutableStateFlow(GreetingsScreenState(null))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    fun changeUserData() = viewModelScope.launch {
        state.value.user?.let { userRepository.updateUserData(it) }
        _state.value = state.value.copy(user = userRepository.getUserById(userId))

        logout()
    }

    fun onNameChanged(newName: String) {
        _state.value = state.value.copy(
            user = state.value.user?.copy(name = newName)
        )
    }

    fun onPassChanged(newPass: String) {
        _state.value = state.value.copy(
            user = state.value.user?.copy(pass = newPass)
        )
    }

    private fun logout() {
        userRepository.forgotUser()
        _state.value = state.value.copy(isLoggedOut = true)
    }

    private suspend fun getUser() {
        val user = userRepository.getUserById(userId)

        if (user != null) {
            _state.value = state.value.copy(user = user)
        } else {
            setToastText(R.string.something_went_wrong)
        }
    }
}
