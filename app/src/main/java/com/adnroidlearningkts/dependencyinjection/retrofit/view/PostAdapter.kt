package com.adnroidlearningkts.dependencyinjection.retrofit.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.HiltRetrofitPostItemBinding
import com.adnroidlearningkts.dependencyinjection.retrofit.model.pojo.Post

class PostAdapter(private val posts: List<Post>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding: HiltRetrofitPostItemBinding = HiltRetrofitPostItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = posts.size

    inner class ViewHolder(val binding: HiltRetrofitPostItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.hiltRetrofitPostItemTitle.text = post.title
            binding.hiltRetrofitPostItemBody.text = post.body
        }
    }
}