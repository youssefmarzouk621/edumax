package com.example.edumax.storage

import android.content.Context
import androidx.room.Room

object AppDB {
    fun init(context:Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "edumax-database"
        ).build()
    }
}