package com.adnroidlearningkts.paging.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.PagingLoadStateItemBinding

class PagingLoadStateAdapter(private val retryCallback: () -> Unit): LoadStateAdapter<PagingLoadStateAdapter.ViewHolder>() {
    override fun onBindViewHolder(
        holder: ViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ViewHolder {
        val binding = PagingLoadStateItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: PagingLoadStateItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            when(loadState) {
                is LoadState.Error -> {
                    val errorState = loadState
                    binding.pagingErrorMsg.text = errorState.error.localizedMessage
                    binding.pagingRetryButton.visibility = View.VISIBLE
                    binding.pagingErrorMsg.visibility = View.VISIBLE
                    binding.pagingProgressbar.visibility = View.GONE
                }
                is LoadState.Loading -> {
                    binding.pagingRetryButton.visibility = View.GONE
                    binding.pagingErrorMsg.visibility = View.GONE
                    binding.pagingProgressbar.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    binding.pagingRetryButton.visibility = View.GONE
                    binding.pagingErrorMsg.visibility = View.GONE
                    binding.pagingProgressbar.visibility = View.GONE
                }
            }

            binding.pagingRetryButton.setOnClickListener {
                retryCallback()
            }
        }
    }
}