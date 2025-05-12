package com.adnroidlearningkts.mvvm.roomdb.notetaking.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityNoteTakingBinding
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.NoteDatabase
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.NoteRepo
import com.adnroidlearningkts.mvvm.roomdb.notetaking.viewmodel.NoteViewModel
import com.adnroidlearningkts.mvvm.roomdb.notetaking.viewmodel.NoteViewModelFactory

class NoteTakingActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityNoteTakingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_note_taking)
        binding = ActivityNoteTakingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViewModel()
    }

    private fun initViewModel() {
        val noteRepo = NoteRepo(NoteDatabase(this))

        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepo)

        noteViewModel = ViewModelProvider(this, viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}