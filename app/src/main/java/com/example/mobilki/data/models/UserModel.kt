package com.example.mobilki.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Users")
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val phoneCode: String,
    val phoneNumber: String,
    val name: String,
    val pass: String,
    val isAdmin: Boolean
)
