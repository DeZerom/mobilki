package com.example.mobilki.presentation.main_activity

import androidx.lifecycle.viewModelScope
import com.example.mobilki.data.repository.SessionRepository
import com.example.mobilki.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
): BaseViewModel() {

    private val _sideEffects = MutableSharedFlow<MainActivitySideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    fun processTouchEvent() = viewModelScope.launch {
        if (sessionRepository.isSessionActive()) {
            sessionRepository.updateLastActiveTime()
        } else {
            sessionRepository.forgotUser()
            _sideEffects.emit(MainActivitySideEffect.GoToLoginScreen)
        }
    }

    fun writeLastActiveTimeIfPossible() {
        if (sessionRepository.isSessionActive()) {
            sessionRepository.updateLastActiveTime()
        }
    }

}
