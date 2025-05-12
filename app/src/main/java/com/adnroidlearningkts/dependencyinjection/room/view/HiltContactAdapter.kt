package com.adnroidlearningkts.dependencyinjection.room.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.HiltContactItemLayoutBinding
import com.adnroidlearningkts.dependencyinjection.room.model.room.HiltContact

/**
 * Use DiffUtil or ListAdapter
 *
 * ListAdapter: is a RecyclerView Adapter optimized for handling lists that
 *  uses DiffUtil behind the scenes. ListAdapter handles item updates efficiently by
 *  calculating the differences between lists and dispatching granular update operations to the RecyclerView.
 *
 * DiffUtil.ItemCallback<HiltContact>: This is a crucial part of DiffUtil, which defines how
 *  DiffUtil determines if items are the same (areItemsTheSame) and if their contents are the same (areContentsTheSame).
 *
 * DIFF_CALLBACK: A companion object holds an instance of the DiffUtil.ItemCallback.
 *  This instance is passed to the ListAdapter's constructor, enabling it to use DiffUtil for list updates.
 *
 * In the constructor, passing the lambda function `onDeleteContact`, the fun will be invoked
 *  when the delete button for a contact is clicked, allowing the adapter to communicate back to
 *  the owning component (e.g., a Fragment or Activity) about which contact should be deleted.
 */
class HiltContactAdapter(private val onDeleteContact: (HiltContact) -> Unit):
    ListAdapter<HiltContact, HiltContactAdapter.ViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HiltContact>() {
            override fun areItemsTheSame(oldItem: HiltContact, newItem: HiltContact): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
                        && oldItem.phone == newItem.phone
            }

            override fun areContentsTheSame(oldItem: HiltContact, newItem: HiltContact): Boolean {
                return oldItem == newItem
            }
        }
    }

//    private val diffCallback = object : DiffUtil.ItemCallback<HiltContact>() {
//        override fun areItemsTheSame(oldItem: HiltContact, newItem: HiltContact): Boolean {
//            return oldItem.id == newItem.id && oldItem.name == newItem.name
//                    && oldItem.phone == newItem.phone
//        }
//
//        override fun areContentsTheSame(oldItem: HiltContact, newItem: HiltContact): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: HiltContactItemLayoutBinding = HiltContactItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    /**
     * getItem() method is typically part of the ListAdapter or PagedListAdapter implementations,
     * which handle managing the list data and efficiently providing individual items.
     */
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val contact = getItem(position)
        holder.bind(contact)
    }

    /**
     * currentList is a property commonly found in ListAdapter and PagedListAdapter,
     * representing the current list of data being displayed by the adapter.
     *
     * When extends ListAdapter, ListAdapter provides the implementation for getItemCount() by default.
     * It internally manages the list of data and returns the size of that list.
     */
//    override fun getItemCount(): Int = currentList.size


    inner class ViewHolder(val binding: HiltContactItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: HiltContact) {
            binding.hiltContactItemName.text = contact.name
            binding.hiltContactItemPhone.text = contact.phone
            binding.hiltContactItemDelete.setOnClickListener {
                onDeleteContact(contact)
            }
        }
    }
}