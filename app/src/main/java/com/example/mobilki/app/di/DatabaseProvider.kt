package com.example.mobilki.app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mobilki.data.db.TheDatabase
import com.example.mobilki.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseProvider {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TheDatabase {
        return Room.databaseBuilder(context, TheDatabase::class.java, "theDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun getUserDao(database: TheDatabase): UserDao =
        database.getUserDao()

}
