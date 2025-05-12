package com.adnroidlearningkts.mvvm.roomdb.contactmanager.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityContactManagerBinding
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.model.Contact
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.model.ContactDatabase
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.model.ContactRepository
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.viewmodel.ContactViewModel
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.viewmodel.ViewModelFactory

class ContactManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactManagerBinding
    private lateinit var viewModel: ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_contact_manager)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_manager)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //RoomDB
        val dao = ContactDatabase.getInstance(applicationContext).contactDAO
        val repo = ContactRepository(dao)

        //use ViewModelFactory to initialize ViewModel
        val factory = ViewModelFactory(repo)

        //ViewModel
//        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        viewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        binding.contactViewModel = viewModel

        //Required for DataBinding and LiveData integration
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.contactActivityRecyclerview.layoutManager = LinearLayoutManager(this)
        showContactList()
    }

    private fun showContactList() {
        //it: List<Contact>
        viewModel.contacts.observe(this, Observer {
            binding.contactActivityRecyclerview.adapter = ContactAdapter(
                it,
                { selectedItem: Contact -> listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(contact: Contact) {
        Toast.makeText(this, "Selected ${contact.name}", Toast.LENGTH_SHORT).show()
        viewModel.onItemClicked(contact)
    }
}