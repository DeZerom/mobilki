package com.example.mobilki.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobilki.data.models.UserModel

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: UserModel)

    @Query("SELECT * FROM Users WHERE phoneCode = :code AND phoneNumber = :phone AND pass = :pass LIMIT 1")
    suspend fun getUserByPhoneAndPass(code: String, phone: String, pass: String): UserModel?

    @Query("SELECT * FROM Users WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): UserModel?

}
