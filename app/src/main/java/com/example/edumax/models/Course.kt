package com.example.edumax.models

import androidx.room.Embedded
import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Course (
    var _id:String,
    var title: String,
    var image: String,
    var description: String,
    var price: String,
    var mentor: User,
) : Serializable