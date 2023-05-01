package com.example.mobilki.presentation.main_activity

import com.example.mobilki.data.repository.UserRepository
import com.example.mobilki.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    fun writeLastActiveTime() {
        userRepository.saveLastActiveTime()
    }

}
