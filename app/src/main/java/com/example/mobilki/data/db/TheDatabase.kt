package com.example.mobilki.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobilki.data.db.dao.UserDao
import com.example.mobilki.data.models.UserModel
import com.example.mobilki.data.type_converters.BooleanTypeConverter

@Database(
    version = 2,
    entities = [UserModel::class]
)
@TypeConverters(
    value = [BooleanTypeConverter::class]
)
abstract class TheDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

}
