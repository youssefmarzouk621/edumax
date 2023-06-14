package com.example.edumax.ui.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.edumax.R
import com.example.edumax.models.BackendResponse
import com.example.edumax.models.User
import com.example.edumax.services.APIService
import com.example.edumax.services.UserService
import com.example.edumax.ui.activities.LoginActivity
import com.example.edumax.ui.activities.MainActivity
import com.example.edumax.utils.Constants
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    var logoutBtn: Button? = null
    var saveBtn: Button? = null


    var FirstNameInput: TextInputEditText? = null
    var lastNameInput: TextInputEditText? = null
    var emailInput: TextInputEditText? = null
    var passwordInput: TextInputEditText? = null

    var profileImage: ImageView? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        logoutBtn = view.findViewById(R.id.logout_button)
        saveBtn = view.findViewById(R.id.save_button)


        FirstNameInput= view.findViewById(R.id.FirstNameInput)
        lastNameInput= view.findViewById(R.id.lastNameInput)
        emailInput= view.findViewById(R.id.emailInput)
        passwordInput= view.findViewById(R.id.passwordInput)
        profileImage = view.findViewById(R.id.updateProfileImage)



        val sharedPreferences = view.context.getSharedPreferences(
            Constants.SHARED_PREF_SESSION,
            AppCompatActivity.MODE_PRIVATE
        )
        val userData = sharedPreferences.getString("USER_DATA", null)
        val connectedUser: User? = Gson().fromJson(userData, User::class.java)

        FirstNameInput!!.setText(connectedUser!!.firstName)
        lastNameInput!!.setText(connectedUser!!.lastName)
        emailInput!!.setText(connectedUser!!.email)


        Glide.with(view.context).load(Constants.BASE_URL + connectedUser!!.avatar).into(profileImage!!)





        saveBtn!!.setOnClickListener{
            handleSave(view.context)
        }


        logoutBtn!!.setOnClickListener {
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle(getString(R.string.logout))
            builder.setMessage(R.string.logout_message)
            builder.setPositiveButton("Yes") { _, _ ->
                logout(view.context)
            }
            builder.setNegativeButton("No") { dialogInterface, which ->
                dialogInterface.dismiss()
            }
            builder.create().show()
        }
        return view
    }

    private fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences(
            Constants.SHARED_PREF_SESSION,
            AppCompatActivity.MODE_PRIVATE
        )
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.clear().apply()

        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun handleSave(context:Context){
        val toast = Toast(this@ProfileFragment.context)

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

        //all clear
        saveInDatabase(context)
    }

    private fun saveInDatabase(context:Context){
        val sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_SESSION,
            AppCompatActivity.MODE_PRIVATE
        )
        val token = sharedPreferences.getString("token", "")
        APIService.userService.updateProfile(
            "Bearer ${token!!}",
            UserService.ProfileBody(
                FirstNameInput!!.text.toString(),
                lastNameInput!!.text.toString(),
                emailInput!!.text.toString(),
                passwordInput!!.text.toString()
            )
        ).enqueue(
            object : Callback<BackendResponse> {
                override fun onResponse(call: Call<BackendResponse>, response: Response<BackendResponse>) {
                    if (response.code() == 200) {
                        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                        val json = Gson().toJson(response.body()!!.user!!)
                        sharedPreferencesEditor.putString("USER_DATA", json)
                        sharedPreferencesEditor.apply()
                        val toast = Toast(this@ProfileFragment.context)
                        toast.setText(response.body()!!.message)
                        toast.show()
                    } else {

                        val toast = Toast(this@ProfileFragment.context)
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