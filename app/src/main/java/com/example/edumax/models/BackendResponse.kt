package com.example.edumax.models

import java.io.Serializable

data class BackendResponse(
    val status: Number,
    val message: String,
    val user:User?
) : Serializable
