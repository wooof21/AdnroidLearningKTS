package com.adnroidlearningkts.mvvm.roomdb.notetaking.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.Visibility
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.FragmentNoteHomeBinding
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.Note
import com.adnroidlearningkts.mvvm.roomdb.notetaking.viewmodel.NoteViewModel


class NoteHomeFragment : Fragment(R.layout.fragment_note_home), SearchView.OnQueryTextListener {

    private var _binding: FragmentNoteHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_note_home, container, false)
        _binding = FragmentNoteHomeBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    /**
     * Initializing ViewModel and setup RecyclerView
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as NoteTakingActivity).noteViewModel

        setupRecyclerView()

        binding.fragmentNoteHomeFab.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_noteHomeFragment_to_noteNewFragment
            )
            //Or
//            val direction = NoteHomeFragmentDirections.actionNoteHomeFragmentToNoteNewFragment()
//            it.findNavController().navigate(direction)
        }
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteRVAdapter()

        /**
         * .apply { ... }: Kotlin scope function that allows to configure multiple properties of the RecyclerView within the block.
         */
        binding.fragmentNoteHomeRecyclerview.apply {
            /**
             * StaggeredGridLayout: Items in a staggered grid can have different heights,
             * allowing for a more visually interesting layout than a standard grid where all items have the same height.
             *
             * 2 - columns
             * StaggeredGridLayoutManager.VERTICAL - orientation
             */
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            //This assigns an Adapter to the RecyclerView.
            adapter = noteAdapter
        }

        /**
         * activity?.let { ... }: This is a safe call (?.) followed by a let scope function.
         * It ensures that the code inside the let block is only executed if activity is not null.
         *
         * viewLifecycleOwner: When the viewLifecycleOwner is in a started or resumed state,
         * the observer will receive updates.
         * When the view is destroyed, the observation will be automatically removed, preventing memory leaks.
         *
         * submitList(notes): submits the new list of notes to the DiffUtil process.
         * `DiffUtil` is a utility class that calculates the difference between two lists and
         * dispatches a minimal set of update operations to the RecyclerView adapter.
         * This allows for smooth animations and efficient updates when the data changes,
         * avoiding unnecessary full refreshes of the list.
         */
        activity?.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner) {
                notes -> noteAdapter.differ.submitList(notes)
                updateUI(notes)
            }
        }
    }

    private fun updateUI(notes: List<Note>?) {
        if (notes != null) {
            if(notes.isNotEmpty()) {
                binding.fragmentNoteHomeCardview.visibility = View.GONE
                binding.fragmentNoteHomeRecyclerview.visibility = View.VISIBLE
            } else {
                binding.fragmentNoteHomeCardview.visibility = View.VISIBLE
                binding.fragmentNoteHomeRecyclerview.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(
            R.menu.note_home_menu, menu
        )

        val menuSearch = menu.findItem(R.id.note_home_menu_search).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        searchNote(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null) {
            searchNote(newText)
        }
        return true
    }

    private fun searchNote(query: String?) {
        /**
         * % Wildcard: The percentage sign (%) is a wildcard character in SQL's LIKE operator.
         * It represents zero or more characters.
         *
         * Common Uses of % with LIKE for Search:
         * 'searchTerm%': Finds values that start with "searchTerm".
         * '%searchTerm': Finds values that end with "searchTerm".
         * '%searchTerm%': Finds values that contain "searchTerm" anywhere within the string.
         */
        val searchQuery = "%$query%"
        noteViewModel.searchNote(searchQuery).observe(this) {
            list -> noteAdapter.differ.submitList(list)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


