package com.adnroidlearningkts.recyclerview.advanced.multiviewtype

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityMultiViewBinding

class MultiViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultiViewBinding
    private val viewModel: MultiTypeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_multi_view)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_multi_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.advRvMultiViewtypeRecyclerview.layoutManager = LinearLayoutManager(this)

        viewModel.getAllEmployees()
        viewModel.employees.observe(this) {
            val adapter = MultiViewAdapter(it)
            binding.advRvMultiViewtypeRecyclerview.adapter = adapter
        }

    }
}