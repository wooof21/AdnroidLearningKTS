package com.adnroidlearningkts.mvvm.roomdb.notetaking.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.NoteItemLayoutBinding
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.Note

class NoteRVAdapter(): RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {


    /**
     * object : DiffUtil.ItemCallback<Note>():
     * This creates an anonymous object that inherits from and implements the DiffUtil.ItemCallback abstract class.
     *
     * `DiffUtil`: an utility class that used with RecyclerView's ListAdapter and AsyncListDiffer
     * - to efficiently calculate the differences between two lists.
     * Instead of refreshing the entire list when data changes, DiffUtil determines exactly
     * which items have been added, removed, or changed. This allows the RecyclerView to
     * update only the affected items with smooth animations,
     * leading to a better user experience and improved performance.
     *
     * https://developer.android. com/ reference/ androidx/ recyclerview/ widget/ DiffUtil. ItemCallback
     * https://www.kodeco.com/ 21954410- speed- up- your- android- recyclerview- using- diffutil
     */
    private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
                    && oldItem.content == newItem.content
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: NoteItemLayoutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.note_item_layout,
            parent, false
        )
        return ViewHolder(binding)

        //or
//        return ViewHolder(NoteItemLayoutBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent, false
//        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNode = differ.currentList[position]
        holder.bind(currentNode)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class ViewHolder(val binding: NoteItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.noteItemTitleTv.text = note.title
            binding.noteItemBodyTv.text = note.content

            val random = java.util.Random()
            val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            binding.noteItemTitleColor.setBackgroundColor(color)


            binding.noteItemTitleLinearlayout.setOnClickListener {
                val direction = NoteHomeFragmentDirections
                            .actionNoteHomeFragmentToNoteUpdateFragment(note)
                //it --> view
                it.findNavController().navigate(direction)
            }
        }
    }
}