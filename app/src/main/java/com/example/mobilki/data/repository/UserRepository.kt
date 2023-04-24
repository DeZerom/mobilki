package com.example.mobilki.data.repository

import android.content.SharedPreferences
import com.example.mobilki.data.db.dao.UserDao
import com.example.mobilki.data.models.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val sharedPreferences: SharedPreferences
) {

    suspend fun updateUserData(user: UserModel) {
        return userDao.updateUser(user = user)
    }

    suspend fun getUserById(id: Int): UserModel? {
        return userDao.getById(id)
    }

    suspend fun addUser(user: UserModel) {
        userDao.addUser(user)
    }

    suspend fun checkCredentials(code: String, phone: String, pass: String): Int? {
        return userDao.getUserByPhoneAndPass(code, phone, pass)?.id
    }

    fun rememberUser(code: String, phone: String, pass: String) {
        sharedPreferences.edit()
            .putString(CODE_KEY, code)
            .putString(NUMBER_KEY, phone)
            .putString(PASS_KEY, pass)
            .putLong(TIME_KEY, System.currentTimeMillis())
            .apply()
    }

    fun forgotUser() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    suspend fun restoreCredentials(): UserModel? {
        val code = sharedPreferences.getString(CODE_KEY, "") ?: return null
        val number = sharedPreferences.getString(NUMBER_KEY, "") ?: return null
        val pass = sharedPreferences.getString(PASS_KEY, "") ?: return null

        val writeTime = sharedPreferences.getLong(TIME_KEY, 0)

        if (writeTime + EXPIRE_TIME <= System.currentTimeMillis()) return null

        return userDao.getUserByPhoneAndPass(code, number, pass)
    }

    companion object {
        private const val CODE_KEY = "code"
        private const val NUMBER_KEY = "number"
        private const val PASS_KEY = "pass"
        private const val TIME_KEY = "time"
        private const val MILLIS_IN_MINUTE = 1000 * 60
        private const val EXPIRE_TIME = 2 * MILLIS_IN_MINUTE
    }

}
