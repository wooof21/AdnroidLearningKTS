package com.adnroidlearningkts.mvvm.roomdb.notetaking.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.FragmentNoteHomeBinding
import com.adnroidlearningkts.databinding.FragmentNoteNewBinding
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.Note
import com.adnroidlearningkts.mvvm.roomdb.notetaking.viewmodel.NoteViewModel

class NoteNewFragment : Fragment(R.layout.fragment_note_new) {

    private var _binding: FragmentNoteNewBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_note_new, container, false)
        _binding = FragmentNoteNewBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as NoteTakingActivity).noteViewModel
        mView = view
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.fragmentNoteNewTitleEt.text.toString().trim()
        val noteBody = binding.fragmentNoteNewContentEt.text.toString().trim()
        if(noteTitle.isNotEmpty()) {
            val newNote = Note(0, noteTitle, noteBody)
            val id = noteViewModel.insertNote(newNote)
            Toast.makeText(context, "Note $id Saved Successfully", Toast.LENGTH_SHORT).show()

            view.findNavController().navigate(R.id.action_noteNewFragment_to_noteHomeFragment)
        } else {
            Toast.makeText(context, "Note Title Cannot Be Empty", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.note_newnote_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.note_newnote_save -> saveNote(mView)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}