package com.example.mobilki.data.repository

import com.example.mobilki.data.db.dao.UserDao
import com.example.mobilki.data.models.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
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
        return getUserByPhoneAndPass(code, phone, pass)?.id
    }

    suspend fun getUserByPhoneAndPass(code: String, phone: String, pass: String): UserModel? {
        return userDao.getUserByPhoneAndPass(code, phone, pass)
    }

    suspend fun getAllUsers(): List<UserModel> {
        return userDao.getAllUsers() ?: emptyList()
    }

}
