package com.example.edumax.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edumax.R
import com.example.edumax.models.Promotion
import com.example.edumax.utils.Constants

class PromotionAdapter(var items: List<Promotion>) :
    RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.promotion_item, parent, false)
        return PromotionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class PromotionViewHolder(view: View) :RecyclerView.ViewHolder(view) {

        private val promotionImage: ImageView = itemView.findViewById(R.id.promotion_image)
        private val promotionTitle: TextView = itemView.findViewById(R.id.promotion_title)
        private val promotionSubtitle: TextView = itemView.findViewById(R.id.promotion_subtitle)
        private val promotionDescription: TextView = itemView.findViewById(R.id.promotion_description)
        private val promotionPercentage: TextView = itemView.findViewById(R.id.promotion_percentage)


        fun bindView(item: Promotion) {
            Glide.with(this.itemView.context).load(Constants.BASE_URL + item.image).into(promotionImage)
            promotionTitle.text = item.title
            promotionSubtitle.text = item.subtitle
            promotionDescription.text = item.description
            promotionPercentage.text = item.percentage
        }
    }
}