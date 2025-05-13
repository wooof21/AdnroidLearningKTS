package com.adnroidlearningkts.paging.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.PagingMovieItemBinding
import com.adnroidlearningkts.paging.model.pojo.PMovie

/**
 * 4. Define a RecyclerView adapter
 *
 * Need to set up an adapter to receive the data into the RecyclerView list.
 * The Paging library provides the PagingDataAdapter class for this purpose.
 *
 */
const val LOADING_ITEM = 0
const val MOVIE_ITEM = 1

class PMovieAdapter(): PagingDataAdapter<PMovie, PMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val binding = PagingMovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    //https://proandroiddev.com/android-paging-library-with-multiple-view-type-68f85fe1222d
    override fun getItemViewType(position: Int): Int {
        return if(position == getItemCount()) MOVIE_ITEM else LOADING_ITEM
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PMovie>() {
            override fun areItemsTheSame(
                oldItem: PMovie,
                newItem: PMovie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PMovie,
                newItem: PMovie
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    inner class ViewHolder(val binding: PagingMovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: PMovie?) {
            binding.movie = movie
        }
    }
}