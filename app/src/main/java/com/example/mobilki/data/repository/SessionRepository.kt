package com.example.mobilki.data.repository

import android.content.SharedPreferences
import com.example.mobilki.data.models.UserModel
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun startSession(phoneCode: String?, phoneNumber: String?, pass: String?) {
        if (phoneCode != null && phoneNumber != null && pass != null) {
            sharedPreferences.edit()
                .putString(CODE_KEY, phoneCode)
                .putString(NUMBER_KEY, phoneNumber)
                .putString(PASS_KEY, pass)
                .apply()
        }

        updateLastActiveTime()
    }

    fun updateLastActiveTime() {
        sharedPreferences.edit()
            .putLong(TIME_KEY, System.currentTimeMillis())
            .apply()
    }

    fun isSessionActive(): Boolean {
        val time = sharedPreferences.getLong(TIME_KEY, 0L)

        return if (time + EXPIRE_TIME <= System.currentTimeMillis()) {
            forgotUser()
            return false
        } else {
            true
        }
    }

    fun getUserSavedData(): UserModel {
        val phoneCode = sharedPreferences.getString(CODE_KEY, "")
        val phoneNumber = sharedPreferences.getString(NUMBER_KEY, "")
        val pass = sharedPreferences.getString(PASS_KEY, "")

        return UserModel(
            id = 0,
            phoneCode = phoneCode ?: "",
            phoneNumber = phoneNumber ?: "",
            pass = pass ?: "",
            name = "",
            isAdmin = false
        )
    }

    fun forgotUser() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    companion object {
        private const val MILLIS_IN_MINUTE = 1000 * 60
        private const val EXPIRE_TIME = 0.5 * MILLIS_IN_MINUTE
        private const val CODE_KEY = "code"
        private const val NUMBER_KEY = "number"
        private const val PASS_KEY = "pass"
        private const val TIME_KEY = "time"
    }

}
