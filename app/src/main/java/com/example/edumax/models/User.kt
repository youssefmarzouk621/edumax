package com.example.edumax.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date


data class User(

    val _id: String,
    val firstName:String,
    val lastName:String,
    val email:String,
    val password:String,
    val phone:String,
    val avatar:String,
    val verified:Int,
    val role:String,
    val token:String?
) : Serializable
