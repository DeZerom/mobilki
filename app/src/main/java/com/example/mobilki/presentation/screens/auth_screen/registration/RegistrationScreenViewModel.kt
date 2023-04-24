package com.example.mobilki.presentation.screens.auth_screen.registration

import androidx.lifecycle.viewModelScope
import com.example.mobilki.R
import com.example.mobilki.data.models.UserModel
import com.example.mobilki.data.repository.UserRepository
import com.example.mobilki.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _state = MutableStateFlow(RegistrationScreenState())
    val state = _state.asStateFlow()

    fun onCodeChanged(newCode: String) {
        _state.value = state.value.copy(code = newCode)
    }

    fun onNumberChanged(newNumber: String) {
        _state.value = state.value.copy(number = newNumber)
    }

    fun onNameChanged(newName: String) {
        _state.value = state.value.copy(name = newName)
    }

    fun onPassChanged(newPass: String) {
        _state.value = state.value.copy(pass = newPass)
    }

    fun onConfirmPassChanged(newConfirmPass: String) {
        _state.value = state.value.copy(confirmPass = newConfirmPass)
    }

    fun onRegButtonClicked() = viewModelScope.launch {
        _state.value = state.value.copy(isLoading = true)

        val state = state.value
        if (validateFields(state)) {
            userRepository.addUser(
                UserModel(
                    id = AUTO_GENERATED_ID,
                    phoneCode = state.code,
                    phoneNumber = state.number,
                    name = state.name,
                    pass = state.pass,
                    isAdmin = false
                )
            )

            _state.value = RegistrationScreenState()
            setToastText(R.string.successful_registration)
        } else {
            _state.value = state.copy(isLoading = false)
        }
    }

    private suspend fun validateFields(state: RegistrationScreenState): Boolean {
        if (state.code.isBlank()
            ||state.number.isBlank()
            ||state.name.isBlank()
            ||state.pass.isBlank()
            ||state.confirmPass.isBlank())
        {
            setToastText(R.string.all_fields_must_be_filled)

            return false
        }

        if (state.pass != state.confirmPass) {
            setToastText(R.string.password_must_match)

            return false
        }

        return true
    }

    companion object {
        private const val AUTO_GENERATED_ID = 0
    }

}
