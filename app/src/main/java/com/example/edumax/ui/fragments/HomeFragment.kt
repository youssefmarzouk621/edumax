package com.example.edumax.ui.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edumax.R
import com.example.edumax.adapters.CourseAdapter
import com.example.edumax.adapters.MentorAdapter
import com.example.edumax.adapters.PromotionAdapter
import com.example.edumax.models.Course
import com.example.edumax.models.Promotion
import com.example.edumax.models.User
import com.example.edumax.services.APIService
import com.example.edumax.utils.Constants
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {


    var promotionRV: RecyclerView? = null
    var mentorsRV: RecyclerView? = null
    var coursesRV:RecyclerView? = null
    var profileImage:ImageView? = null
    var fullNameText:TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        promotionRV = view.findViewById(R.id.promotionsView)
        mentorsRV = view.findViewById(R.id.mentorsRV)
        coursesRV = view.findViewById(R.id.coursesRV)
        profileImage = view.findViewById(R.id.profileImage)
        fullNameText = view.findViewById(R.id.textView4)

        val sharedPreferences = view.context.getSharedPreferences(
            Constants.SHARED_PREF_SESSION,
            AppCompatActivity.MODE_PRIVATE
        )
        val token = sharedPreferences.getString("token", "")
        val userData = sharedPreferences.getString("USER_DATA", null)
        val connectedUser: User? = Gson().fromJson(userData, User::class.java)

        fullNameText!!.text = connectedUser!!.firstName+" "+connectedUser!!.lastName
        Glide.with(view.context).load(Constants.BASE_URL + connectedUser!!.avatar).into(profileImage!!)





        fetchPromotions(token!!)
        fetchMentors()
        fetchCourses()


        return view
    }

    private fun fetchCourses() {

        APIService.courseService.getCourses()
            .enqueue(
                object : Callback<List<Course>> {
                    override fun onResponse(
                        call: Call<List<Course>>,
                        response: Response<List<Course>>
                    ) {
                        if (response.code() == 200) {
                            val courses = response.body()!!
                            coursesRV!!.adapter = CourseAdapter(courses)
                            coursesRV!!.setHasFixedSize(true)
                            coursesRV!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayoutManager.VERTICAL, false)
                        } else {
                            println("status code is " + response.code())
                        }
                    }

                    override fun onFailure(
                        call: Call<List<Course>>,
                        t: Throwable
                    ) {
                        println("HTTP ERROR")
                        t.printStackTrace()
                    }

                }
            )
    }


    private fun fetchMentors() {

        APIService.userService.getMentors()
            .enqueue(
                object : Callback<List<User>> {
                    override fun onResponse(
                        call: Call<List<User>>,
                        response: Response<List<User>>
                    ) {
                        if (response.code() == 200) {
                            val mentors = response.body()!!
                            mentorsRV!!.adapter = MentorAdapter(mentors)
                            mentorsRV!!.setHasFixedSize(true)
                            mentorsRV!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayoutManager.HORIZONTAL, false)
                        } else {
                            println("status code is " + response.code())
                        }
                    }

                    override fun onFailure(
                        call: Call<List<User>>,
                        t: Throwable
                    ) {
                        println("HTTP ERROR")
                        t.printStackTrace()
                    }

                }
            )
    }

    private fun fetchPromotions(token:String) {


        APIService.promotionService.getAll("Bearer $token")
            .enqueue(
                object : Callback<List<Promotion>> {
                    override fun onResponse(
                        call: Call<List<Promotion>>,
                        response: Response<List<Promotion>>
                    ) {
                        if (response.code() == 200) {
                            val promotions = response.body()!!
                            promotionRV!!.adapter = PromotionAdapter(promotions)
                            promotionRV!!.setHasFixedSize(true)
                            promotionRV!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayoutManager.HORIZONTAL, false)
                        } else {
                            println("status code is " + response.code())
                        }
                    }

                    override fun onFailure(
                        call: Call<List<Promotion>>,
                        t: Throwable
                    ) {
                        println("HTTP ERROR")
                        t.printStackTrace()
                    }

                }
            )
    }


}