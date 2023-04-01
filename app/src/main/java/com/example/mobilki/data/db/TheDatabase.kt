package com.example.mobilki.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilki.data.db.dao.UserDao
import com.example.mobilki.data.models.UserModel

@Database(
    version = 1,
    entities = [UserModel::class]
)
abstract class TheDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

}
