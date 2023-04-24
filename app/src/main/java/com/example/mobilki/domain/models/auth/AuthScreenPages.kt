package com.example.mobilki.domain.models.auth

import androidx.annotation.StringRes
import com.example.mobilki.R

enum class AuthScreenPages {
    LOGIN, REGISTRATION;

    @StringRes
    fun toStringRes() = when (this) {
        REGISTRATION -> R.string.registration
        LOGIN -> R.string.login
    }
}
