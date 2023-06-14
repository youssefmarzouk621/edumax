package com.example.edumax.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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

class RegisterActivity : AppCompatActivity() {
    var registerBtn: Button? = null
    var backBtn:Button? = null

    var FirstNameInput: TextInputEditText? = null
    var lastNameInput: TextInputEditText? = null
    var emailInput: TextInputEditText? = null
    var passwordInput: TextInputEditText? = null
    var confirmPasswordInput: TextInputEditText? = null



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        registerBtn = findViewById(R.id.registerBtn)
        backBtn = findViewById(R.id.backBtn)

        FirstNameInput= findViewById(R.id.FirstNameInput)
        lastNameInput= findViewById(R.id.lastNameInput)
        emailInput= findViewById(R.id.emailInput)
        passwordInput= findViewById(R.id.passwordInput)
        confirmPasswordInput= findViewById(R.id.confirmPasswordInput)

        registerBtn!!.setOnClickListener{
            register(it.context)
        }

        backBtn!!.setOnClickListener {
            finish()
        }
    }


    private fun register(context:Context){
        val toast = Toast(context)

        if(FirstNameInput!!.text!!.isEmpty()){
            toast.setText("firstName is required")
            toast.show()
            return
        }
        if(lastNameInput!!.text!!.isEmpty()){
            toast.setText("lastName is required")
            toast.show()
            return
        }
        if(emailInput!!.text!!.isEmpty()){
            toast.setText("email is required")
            toast.show()
            return
        }
        if(passwordInput!!.text!!.isEmpty()){
            toast.setText("password is required")
            toast.show()
            return
        }
        if(confirmPasswordInput!!.text!!.isEmpty()){
            toast.setText("password is required")
            toast.show()
            return
        }
        if(confirmPasswordInput!!.text!! != passwordInput!!.text!!){
            toast.setText("password don't match")
            toast.show()
            return
        }

        //all good
        APIService.userService.register(
            UserService.ProfileBody(
                FirstNameInput!!.text.toString(),
                lastNameInput!!.text.toString(),
                emailInput!!.text.toString(),
                passwordInput!!.text.toString(),
            )
        ).enqueue(
            object : Callback<BackendResponse> {
                override fun onResponse(call: Call<BackendResponse>, response: Response<BackendResponse>) {
                    if (response.code() == 200) {
                        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_SESSION, MODE_PRIVATE)
                        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                        val json = Gson().toJson(response.body()!!.user!!)
                        sharedPreferencesEditor.putString("USER_DATA", json)
                        sharedPreferencesEditor.putString("token", response.body()!!.user!!.token!!)
                        sharedPreferencesEditor.apply()

                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val toast = Toast(this@RegisterActivity)
                        toast.setText(response.body()!!.message)
                        toast.show()
                        println("status code is " + response.code())
                    }
                }
                override fun onFailure(call: Call<BackendResponse>, t: Throwable ) {
                    println("HTTP ERROR")
                    t.printStackTrace()
                }
            }
        )
    }
}