package com.example.edumax.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edumax.R
import com.example.edumax.models.User
import com.example.edumax.utils.Constants

class MentorAdapter(var items: List<User>) :RecyclerView.Adapter<MentorAdapter.MentorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mentor_item, parent, false)
        return MentorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class MentorViewHolder(view: View) :RecyclerView.ViewHolder(view) {

        private val mentorImage: ImageView = itemView.findViewById(R.id.mentorImage)
        private val mentorName: TextView = itemView.findViewById(R.id.mentorName)


        fun bindView(item: User) {
            Glide.with(this.itemView.context).load(Constants.BASE_URL + item.avatar).into(mentorImage)
            mentorName.text = item.firstName
        }
    }
}