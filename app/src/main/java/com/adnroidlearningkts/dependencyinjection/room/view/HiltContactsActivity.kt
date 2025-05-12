package com.adnroidlearningkts.dependencyinjection.room.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityHiltContactsBinding
import com.adnroidlearningkts.dependencyinjection.room.model.room.HiltContact
import com.adnroidlearningkts.dependencyinjection.room.viewmodel.HiltContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class HiltContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHiltContactsBinding
    private val viewModel: HiltContactViewModel by viewModels()
    private lateinit var adapter: HiltContactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_hilt_contacts)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hilt_contacts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.activityHiltRoomRecyclerview.layoutManager = LinearLayoutManager(this)

        //initialize the Adapter with its lambda function implementation
        adapter = HiltContactAdapter {
            viewModel.deleteContact(it)
        }

        binding.activityHiltRoomRecyclerview.adapter = adapter

        viewModel.allContacts.observe(this) {
            //Observe the LiveData returned by viewModel.allContacts and sub new list to RecyclerView
            adapter.submitList(it)
        }

        binding.activityHiltRoomAddBtn.setOnClickListener {
            val name = binding.activityHiltRoomNameEt.text.toString().trim()
            val phone = binding.activityHiltRoomPhoneEt.text.toString().trim()
            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
                val contact = HiltContact(name = name, phone = phone)
                viewModel.insertContact(contact)
            }
        }

    }
}