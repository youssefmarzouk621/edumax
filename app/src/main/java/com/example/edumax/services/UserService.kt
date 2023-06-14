package com.example.edumax.services

import com.example.edumax.models.BackendResponse
import com.example.edumax.models.User
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

    interface UserService {

        data class LoginBody(val email: String, val password: String)

        data class ProfileBody(
            val firstName:String,
            val lastName:String,
            val email: String,
            val password: String
        )


        @GET("/api/auth/")
        fun getAll(): Call<List<User>>

        @GET("/api/mentors/")
        fun getMentors(): Call<List<User>>


        @POST("/api/auth/login")
        fun login(@Body loginBody: LoginBody): Call<BackendResponse>

        @POST("/api/auth/register")
        fun register(@Body registerBody: ProfileBody): Call<BackendResponse>


        @POST("/api/auth/updateProfile")
        fun updateProfile(@Header("Authorization") token:String,@Body profileBody: ProfileBody): Call<BackendResponse>

    }