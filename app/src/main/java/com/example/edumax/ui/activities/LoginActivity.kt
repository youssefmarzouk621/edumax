package com.example.edumax.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.edumax.R
import com.example.edumax.models.BackendResponse
import com.example.edumax.services.APIService
import com.example.edumax.services.UserService
import com.example.edumax.utils.Constants
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var emailid: TextInputEditText? = null
    var mdpid: TextInputEditText? = null
    var btncnx: Button? = null
    var btnforgetpassword: Button? = null
    var btnsignup: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        emailid = findViewById(R.id.emailid)
        mdpid = findViewById(R.id.mdpid)
        btncnx = findViewById(R.id.btncnx)
        btnforgetpassword = findViewById(R.id.btnforgetpassword)
        btnsignup = findViewById(R.id.btnsignup)



        btncnx!!.setOnClickListener {
            login()
        }
        btnsignup!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnforgetpassword!!.setOnClickListener {
            //val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            //startActivity(intent)
        }
    }

    private fun login() {
        APIService.userService.login(
            UserService.LoginBody(
                emailid!!.text.toString(),
                mdpid!!.text.toString()
            )
        ).enqueue(
            object : Callback<BackendResponse> {
                override fun onResponse( call: Call<BackendResponse>, response: Response<BackendResponse>) {
                    if (response.code() == 200) {
                        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_SESSION, MODE_PRIVATE)
                        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                        val json = Gson().toJson(response.body()!!.user!!)
                        sharedPreferencesEditor.putString("USER_DATA", json)
                        sharedPreferencesEditor.putString("token", response.body()!!.user!!.token!!)
                        sharedPreferencesEditor.apply()

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val toast = Toast(this@LoginActivity)
                        toast.setText(response.body()!!.message)
                        toast.show()
                        println("status code is " + response.code())
                    }
                }
                override fun onFailure(call: Call<BackendResponse>,t: Throwable ) {
                    println("HTTP ERROR")
                    t.printStackTrace()
                }
            }
        )
    }
}