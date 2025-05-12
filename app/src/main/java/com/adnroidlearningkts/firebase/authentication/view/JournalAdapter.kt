package com.adnroidlearningkts.firebase.authentication.view

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.JournalListItemLayoutBinding
import com.adnroidlearningkts.firebase.authentication.model.Journal
import com.bumptech.glide.Glide

class JournalAdapter(val journals: List<Journal>): RecyclerView.Adapter<JournalAdapter.ViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = JournalListItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val journal: Journal = journals[position]
        holder.bind(journal)
    }

    override fun getItemCount(): Int {
        return journals.size
    }


    inner class ViewHolder(val binding: JournalListItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(journal: Journal) {
            binding.journalItemRowTitle.text = journal.title
            binding.journalItemRowThought.text = journal.thoughts
            binding.journalItemRowUsername.text = journal.username
            binding.journalItemRowTimestamp.text = DateUtils.getRelativeTimeSpanString(journal.timeAdded!!.seconds * 1000)
            Glide.with(binding.root.context)
                .load(journal.imgUrl)
                .fitCenter()
                .into(binding.journalItemRowImage)
        }
    }
}