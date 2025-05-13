package com.adnroidlearningkts.paging.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityPagingBinding
import com.adnroidlearningkts.paging.utils.GridSpace
import com.adnroidlearningkts.paging.view.adapter.LOADING_ITEM
import com.adnroidlearningkts.paging.view.adapter.PMovieAdapter
import com.adnroidlearningkts.paging.view.adapter.PagingLoadStateAdapter
import com.adnroidlearningkts.paging.viewmodel.PMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagingBinding
    private val viewModel: PMovieViewModel by viewModels()
    private lateinit var pMovieAdapter: PMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_paging)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_paging)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        pMovieAdapter = PMovieAdapter()
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.movieListRecyclerView.apply {
            layoutManager = gridLayoutManager
            addItemDecoration(GridSpace(2, 20, true))
        }
        binding.movieListRecyclerView.adapter = pMovieAdapter.withLoadStateFooter(
            PagingLoadStateAdapter {
                    pMovieAdapter.retry()
            }
        )

        // Set a grid span to set the progressBar at the center
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // Assuming PMovieAdapter.LOADING_ITEM is a defined constant
                return if (pMovieAdapter.getItemViewType(position) == LOADING_ITEM) 1 else 2
            }
        }

        viewModel.movies.observe(this) {
            pMovieAdapter.submitData(lifecycle,it)
        }

    }
}