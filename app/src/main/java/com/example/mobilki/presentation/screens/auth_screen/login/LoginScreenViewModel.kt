package com.example.mobilki.presentation.screens.auth_screen.login

import androidx.lifecycle.viewModelScope
import com.example.mobilki.R
import com.example.mobilki.data.repository.UserRepository
import com.example.mobilki.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    init {
        tryRestoreCredentials()
    }

    fun onCodeChange(newCode: String) {
        _state.value = state.value.copy(code = newCode)
    }

    fun onPhoneChange(newPhone: String) {
        _state.value = state.value.copy(phone = newPhone)
    }

    fun onPassChange(newPass: String) {
        _state.value = state.value.copy(pass = newPass)
    }

    fun logIn() = viewModelScope.launch {
        _state.value = state.value.copy(isLoading = true)

        val state = state.value

        val result = userRepository.checkCredentials(
            code = state.code,
            phone = state.phone,
            pass = state.pass
        )

        if (result != null) {
            if (state.rememberMe) {
                rememberUser(state)
            }

            _state.value = LoginScreenState()
            navigateToSomeScreen(result)
        } else {
            _state.value = state.copy(isLoading = false)
            setToastText(R.string.wrong_credentials)
        }
    }

    private fun rememberUser(state: LoginScreenState) {
        userRepository.rememberUser(
            code = state.code,
            phone = state.phone,
            pass = state.pass
        )
    }

    private fun tryRestoreCredentials() {
        val userModel = userRepository.restoreCredentials()

        _state.value = state.value.copy(
            code = userModel.phoneCode,
            phone = userModel.phoneNumber,
            pass = userModel.pass
        )
    }

    private fun navigateToSomeScreen(userId: Int) {
        _state.value = state.value.copy(successful = true, userId = userId)
    }
}