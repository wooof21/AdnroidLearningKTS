package com.adnroidlearningkts.dependencyinjection.mvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.EcommerceCategoryProductsItemBinding
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct
import com.bumptech.glide.Glide

class CategoryProductAdapter(private val onProductClicked: (CategoryProduct) -> Unit):
        ListAdapter<CategoryProduct, CategoryProductAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryProduct>() {
            override fun areItemsTheSame(
                oldItem: CategoryProduct,
                newItem: CategoryProduct
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CategoryProduct,
                newItem: CategoryProduct
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = EcommerceCategoryProductsItemBinding.inflate(
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

    inner class ViewHolder(val binding: EcommerceCategoryProductsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: CategoryProduct) {
            binding.ecommerceCategoryProductsItemTitle.text = product.title
            binding.ecommerceCategoryProductsItemPrice.text = product.price.toString()
            Glide.with(binding.root.context)
                .load(product.imageUrl)
                .into(binding.ecommerceCategoryProductsItemImage)

            binding.ecommerceCategoryProductsItemLayout.setOnClickListener {
                onProductClicked(product)
            }
        }
    }
}