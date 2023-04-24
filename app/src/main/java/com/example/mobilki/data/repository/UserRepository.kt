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
            .apply()
    }

    fun restoreCredentials(): UserModel {
        val code = sharedPreferences.getString(CODE_KEY, "")
        val number = sharedPreferences.getString(NUMBER_KEY, "")
        val pass = sharedPreferences.getString(PASS_KEY, "")

        return UserModel(
            id = 0,
            phoneCode = code!!,
            phoneNumber = number!!,
            pass = pass!!,
            name = ""
        )
    }

    companion object {
        private const val CODE_KEY = "code"
        private const val NUMBER_KEY = "number"
        private const val PASS_KEY = "pass"
    }

}
