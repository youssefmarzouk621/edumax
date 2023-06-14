package com.example.edumax.services

import com.example.edumax.models.BackendResponse
import com.example.edumax.models.Promotion
import com.example.edumax.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST



interface PromotionService {

    @GET("/api/promotions/")
    fun getAll(@Header("Authorization") token:String): Call<List<Promotion>>



}