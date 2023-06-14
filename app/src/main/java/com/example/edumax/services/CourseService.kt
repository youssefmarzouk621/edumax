package com.example.edumax.services
import com.example.edumax.models.BackendResponse
import com.example.edumax.models.Course
import com.example.edumax.models.Promotion
import com.example.edumax.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface CourseService {

    @GET("/api/courses/")
    fun getCourses(): Call<List<Course>>

    @GET("/api/courses/{id}")
    fun getCourseById(@Path("id") courseId:String ): Call<Course>


}