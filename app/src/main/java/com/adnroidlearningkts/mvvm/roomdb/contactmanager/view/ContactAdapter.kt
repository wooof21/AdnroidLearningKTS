package com.adnroidlearningkts.mvvm.roomdb.contactmanager.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ContactItemLayoutBinding
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.model.Contact

/**
 * clickHandler -> an lambda expression that take `Contact` as parameter and return void
 */
class ContactAdapter(private val contacts: List<Contact>,
                     private val clickHandler: (Contact) -> Unit):
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ContactItemLayoutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.contact_item_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position], clickHandler)
    }


    inner class ViewHolder(val binding: ContactItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact, clickHandler: (Contact) -> Unit) {
            binding.contactItemName.text = contact.name
            binding.contactItemEmail.text = contact.email

            binding.contactItemLinearlayout.setOnClickListener {
                clickHandler(contact)
            }
        }
    }
}