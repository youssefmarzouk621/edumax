package com.example.edumax.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.edumax.R
import com.example.edumax.storage.AppDB
import com.example.edumax.storage.dao.CourseDao


class FavorisFragment : Fragment() {

    private lateinit var courseDao: CourseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_favoris, container, false)



        return view
    }


}