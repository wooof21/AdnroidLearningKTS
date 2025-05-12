package com.adnroidlearningkts.mvvm.roomdb.notetaking.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.FragmentNoteNewBinding
import com.adnroidlearningkts.databinding.FragmentNoteUpdateBinding
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.Note
import com.adnroidlearningkts.mvvm.roomdb.notetaking.viewmodel.NoteViewModel


class NoteUpdateFragment : Fragment(R.layout.fragment_note_update) {

    private var _binding: FragmentNoteUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote: Note
    //safe args - refer to the argument in nav graph
    private val args: NoteUpdateFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_note_update, container, false)
        _binding = FragmentNoteUpdateBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as NoteTakingActivity).noteViewModel

        currentNote = args.note!!

        binding.fragmentNoteUpdateTitleEt.setText(currentNote.title)
        binding.fragmentNoteUpdateContentEt.setText(currentNote.content)

        //when user updated the note
        binding.fragmentNoteUpdateFab.setOnClickListener {
            val noteTitle = binding.fragmentNoteUpdateTitleEt.text.toString().trim()
            val noteBody = binding.fragmentNoteUpdateContentEt.text.toString().trim()

            if(noteTitle.isNotEmpty()) {
                //update note by id
                val newNote = Note(currentNote.id, noteTitle, noteBody)
                val id = noteViewModel.updateNode(newNote)
                Toast.makeText(context, "Note $id Updated Successfully", Toast.LENGTH_SHORT).show()

                view.findNavController().navigate(R.id.action_noteUpdateFragment_to_noteHomeFragment)
            } else {
                Toast.makeText(context, "Note Title Cannot Be Empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNoteAction() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Delete Note")
            setMessage("Are you sure?")
            setPositiveButton("Delete") {_, _, ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(R.id.action_noteUpdateFragment_to_noteHomeFragment)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.note_updatenote_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.note_updatenote_delete -> deleteNoteAction()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}