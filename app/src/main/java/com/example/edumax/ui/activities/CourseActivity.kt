package com.example.edumax.ui.activities

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.edumax.R
import com.example.edumax.adapters.CourseAdapter
import com.example.edumax.models.Course
import com.example.edumax.services.APIService
import com.example.edumax.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseActivity : AppCompatActivity() {
    var courseImage: ImageView? = null
    var title: TextView? = null
    var mentorImage: ImageView? = null
    var mentorFullname: TextView? = null
    var description: TextView? = null
    var favButton: AppCompatImageButton? = null
    var price: TextView? = null
    var enrollButton: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        val extras = intent.extras
        val courseId = extras!!.getString("courseId")


        courseImage = findViewById(R.id.course_detail_image)
        title = findViewById(R.id.course_detail_title)
        mentorImage = findViewById(R.id.course_detail_mentor_image)
        mentorFullname = findViewById(R.id.course_detail_mentor_fullname)
        description = findViewById(R.id.course_detail_description)
        favButton = findViewById(R.id.fav_button)

        price = findViewById(R.id.course_detail_mentor_price)
        enrollButton = findViewById(R.id.enroll_button)



        fetchCourseById(courseId!!)



    }

    private fun fetchCourseById(courseId:String) {

        APIService.courseService.getCourseById(courseId)
            .enqueue(
                object : Callback<Course> {
                    override fun onResponse(
                        call: Call<Course>,
                        response: Response<Course>
                    ) {
                        if (response.code() == 200) {
                            val course = response.body()!!
                            Glide.with(this@CourseActivity).load(Constants.BASE_URL + course.image).into(courseImage!!)
                            title!!.text = course.title
                            Glide.with(this@CourseActivity).load(Constants.BASE_URL + course.mentor.avatar).into(mentorImage!!)
                            mentorFullname!!.text = course.mentor.firstName+" "+ course.mentor.lastName
                            description!!.text = course.description
                            price!!.text = course.price
                        } else {
                            println("status code is " + response.code())
                        }
                    }

                    override fun onFailure(
                        call: Call<Course>,
                        t: Throwable
                    ) {
                        println("HTTP ERROR")
                        t.printStackTrace()
                    }
                }
            )
    }
}