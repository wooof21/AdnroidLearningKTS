package com.adnroidlearningkts.dependencyinjection.mvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.EcommerceCategoryItemBinding
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.Category

class CategoryAdapter(
    private val onCategoryClicked: (String) -> Unit): ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = EcommerceCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.bind(getItem(position))
    }


    inner class ViewHolder(val binding: EcommerceCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.ecommerceCategoryItemName.text = category.name
            binding.ecommerceCategoryItemImage.setImageResource(category.categoryImg)
            binding.ecommerceCategoryItemLayout.setOnClickListener {
                onCategoryClicked(category.name)
            }
        }
    }
}