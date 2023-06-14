package com.example.edumax.storage.entities

import com.example.edumax.models.User
import androidx.room.Embedded
import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var _id:String,
    var title: String,
    var image: String,
    var description: String,
    var price: String,
    var mentorId: Int,
)





