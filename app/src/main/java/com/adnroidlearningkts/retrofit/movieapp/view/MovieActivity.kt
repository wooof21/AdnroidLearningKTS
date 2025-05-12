package com.adnroidlearningkts.retrofit.movieapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityMoiveBinding
import com.adnroidlearningkts.retrofit.movieapp.model.apiinterface.MovieInterface
import com.adnroidlearningkts.retrofit.movieapp.model.pojo.Movie
import com.adnroidlearningkts.retrofit.movieapp.model.pojo.MovieResult
import com.adnroidlearningkts.retrofit.movieapp.model.repository.MovieRepo
import com.adnroidlearningkts.retrofit.movieapp.viewmodel.MovieViewModel
import com.adnroidlearningkts.retrofit.movieapp.viewmodel.MovieViewModelFactory
import retrofit2.Response

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoiveBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movies: List<Movie>
    private lateinit var movieApi: MovieInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_moive)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_moive)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.movie_list_recycler_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val repo: MovieRepo = MovieRepo()
        val viewModelFactory = MovieViewModelFactory(application, repo)
        movieViewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)


        getPopularMovies()

        binding.movieListSwipetorefreshLayout.setColorSchemeResources(R.color.blue)
        binding.movieListSwipetorefreshLayout.setOnRefreshListener {
            getPopularMovies()
        }
    }

    fun getPopularMovies() {
        /**
         * liveData: fetch data asynchronously
         *
         * liveData { ... } block is a Kotlin coroutine builder provided by the lifecycle-livedata-ktx library.
         * It allows to perform asynchronous operations within a LiveData builder block and emit the results as LiveData values.
         */
        val responseLiveData: LiveData<Response<MovieResult>> = liveData {
            val moviesRes = movieViewModel.getPopularMovies("76df037c3dc6f00226909bdc3fb75127")
            emit(moviesRes)
        }

        responseLiveData.observe(this) {
            if(it.isSuccessful) {
                movies = it.body()?.movies!!
                renderRecyclerView()
                //dismiss loading spinner when refreshing done
                binding.movieListSwipetorefreshLayout.isRefreshing = false
            }
        }
    }

    fun renderRecyclerView() {
        adapter = MovieAdapter(movies)

        val layoutManager = GridLayoutManager(this, 2)
        binding.movieListRecyclerView.layoutManager = layoutManager

        binding.movieListRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.movieListRecyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }
}