package com.example.edumax.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.edumax.models.Course
import com.example.edumax.models.User
import com.example.edumax.storage.dao.CourseDao
import com.example.edumax.storage.entities.CourseEntity

@Database(entities = [CourseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}