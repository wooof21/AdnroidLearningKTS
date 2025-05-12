package com.adnroidlearningkts.dependencyinjection.retrofit.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityPostsBinding
import com.adnroidlearningkts.dependencyinjection.retrofit.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint


//https://jsonplaceholder.typicode.com/posts
@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    //lazy inject the ViewModel
    private val viewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_posts)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.activityPostRv.layoutManager = LinearLayoutManager(this)
        //Add a divider to RecyclerView
        binding.activityPostRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //obverse the data
        viewModel.getPosts()
        viewModel.posts.observe(this) {
            val adapter = PostAdapter(it)
            binding.activityPostRv.adapter = adapter
        }
    }
}