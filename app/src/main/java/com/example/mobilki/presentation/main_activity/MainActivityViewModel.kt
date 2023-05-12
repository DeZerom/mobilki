package com.example.mobilki.presentation.main_activity

import com.example.mobilki.data.repository.SessionRepository
import com.example.mobilki.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
): BaseViewModel() {

    fun writeLastActiveTime() {
        sessionRepository.updateLastActiveTime()
    }

}
