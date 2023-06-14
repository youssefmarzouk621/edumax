package com.example.edumax.services

import com.example.edumax.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    val userService: UserService by lazy {
        retrofit().create(UserService::class.java)
    }

    val promotionService: PromotionService by lazy {
        retrofit().create(PromotionService::class.java)
    }

    val courseService: CourseService by lazy {
        retrofit().create(CourseService::class.java)
    }

}