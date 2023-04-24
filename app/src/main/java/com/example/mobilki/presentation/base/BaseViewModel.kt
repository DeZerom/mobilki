package com.example.mobilki.presentation.base

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel: ViewModel() {

    private val _toastRes = Channel<Int>()
    val toastRes = _toastRes.receiveAsFlow()

    suspend fun setToastText(@StringRes res: Int) {
        _toastRes.send(0)
        _toastRes.send(res)
    }

}
