package com.adnroidlearningkts.recyclerview.advanced.cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.R

class SportsAdapter(private val sports: List<Sport>): RecyclerView.Adapter<SportsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sports_card_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sports.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sport = sports[position]

        holder.iv.setBackgroundResource(sport.img)
        holder.tv.text = sport.name
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv: ImageView = itemView.findViewById(R.id.sport_card_item_img)
        val tv: TextView = itemView.findViewById(R.id.sport_card_item_title)

        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Choose: ${sports[adapterPosition].name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}