package com.example.edumax.storage.dao

import androidx.room.*
import com.example.edumax.models.Course
import com.example.edumax.storage.entities.CourseEntity


@Dao
interface CourseDao {
    @Insert
    fun insert(course: CourseEntity)

    @Update
    fun update(course: CourseEntity)

    @Delete
    fun delete(course: CourseEntity)

    @Query("SELECT * FROM courses")
    fun getAllCourses(): List<CourseEntity>
}