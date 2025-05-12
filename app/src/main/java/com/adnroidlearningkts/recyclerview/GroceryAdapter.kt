package com.adnroidlearningkts.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.R
import com.adnroidlearningkts.recyclerview.model.Grocery

class GroceryAdapter(private val groceries: List<Grocery>): RecyclerView.Adapter<GroceryAdapter.ViewHolder>() {


    /**
     * called when RV needs a new ViewHolder Instance
     * Responsible for inflating the layout for a single item and returning a new ViewHolder
     *
     * parent: parent view that the new view will be attached to after it is bound to data
     * viewType: used to distinguish between different view types
     *      * not used if there is only one type of item view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.grocery_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return groceries.size
    }

    /**
     * called by RV to bind data to the ViewHolder at a specific position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grocery = groceries[position]
        holder.iv.setImageResource(grocery.img)
        holder.titleTv.text = grocery.name
        holder.descTv.text = grocery.desc
    }

    /**
     * `inner`: used to indicate that the class is an inner class, meaning it is nested
     * within another class (GroceryAdapter)
     *
     * `itemView`: refer to the individual view item in RecyclerView
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv: ImageView
        val titleTv: TextView
        val descTv: TextView

        init {
            iv = itemView.findViewById(R.id.grocery_item_img)
            titleTv = itemView.findViewById(R.id.grocery_item_title)
            descTv = itemView.findViewById(R.id.grocery_item_description)

            //Handle item view click event
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Clicked On ${groceries[adapterPosition].name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}