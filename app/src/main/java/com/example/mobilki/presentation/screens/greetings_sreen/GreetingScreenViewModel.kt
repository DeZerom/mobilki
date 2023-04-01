package com.example.mobilki.presentation.screens.greetings_sreen

import androidx.lifecycle.SavedStateHandle
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
class GreetingScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val userId: Int = savedStateHandle["userId"] ?: 0

    private val _state = MutableStateFlow(GreetingsScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    private suspend fun getUser() {
        val user = userRepository.getUserById(userId)

        if (user != null) {
            _state.value = state.value.copy(name = user.name)
        } else {
            setToastText(R.string.something_went_wrong)
        }
    }
}
