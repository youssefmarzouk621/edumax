package com.example.edumax.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edumax.R
import com.example.edumax.models.Course
import com.example.edumax.ui.activities.CourseActivity
import com.example.edumax.ui.activities.LoginActivity
import com.example.edumax.utils.Constants

class CourseAdapter(var items: List<Course>) :RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CourseViewHolder(view: View) :RecyclerView.ViewHolder(view) {

        private val courseImage: ImageView = itemView.findViewById(R.id.courseImage)
        private val courseTitle: TextView = itemView.findViewById(R.id.courseTitle)
        private val courseDescription: TextView = itemView.findViewById(R.id.courseDescription)
        private val coursePrice: TextView = itemView.findViewById(R.id.coursePrice)


        fun bindView(item: Course) {
            Glide.with(this.itemView.context).load(Constants.BASE_URL + item.image).into(courseImage)
            courseTitle.text = item.title
            courseDescription.text = item.description
            coursePrice.text = item.price

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CourseActivity::class.java)
                intent.putExtra("courseId",item._id)
                itemView.context.startActivity(intent)
            }
        }
    }
}