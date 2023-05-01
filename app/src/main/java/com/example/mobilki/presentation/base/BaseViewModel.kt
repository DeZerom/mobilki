package com.example.mobilki.presentation.base

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    private val _toastRes = mutableStateOf<Int?>(null)
    val toastRes: State<Int?> = _toastRes

    fun setToastText(@StringRes res: Int) {
        _toastRes.value = res
    }

}
