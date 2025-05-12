package com.adnroidlearningkts.dependencyinjection.mvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.EcommerceCartItemBinding
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct
import com.bumptech.glide.Glide

class CartAdapter(
    private val onItemRemove: (CategoryProduct) -> Unit): ListAdapter<CategoryProduct, CartAdapter.ViewHolder>(DIFF_CALLBACK) {

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
        val binding = EcommerceCartItemBinding.inflate(
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


    inner class ViewHolder(val binding: EcommerceCartItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: CategoryProduct) {
            binding.ecommerceCartItemTitle.text = product.title
            binding.ecommerceCartItemPrice.text = product.price.toString()
            Glide.with(binding.root.context)
                .load(product.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ecommerceCartItemImage)

            binding.ecommerceCartItemRemove.setOnClickListener {
                onItemRemove(product)
            }
        }
    }
}