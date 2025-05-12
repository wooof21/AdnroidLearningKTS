package com.adnroidlearningkts.dependencyinjection.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityEcommerceBinding
import com.adnroidlearningkts.dependencyinjection.mvvm.viewmodel.EcommerceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EcommerceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEcommerceBinding
    private lateinit var adapter: CategoryAdapter
    private val viewModel: EcommerceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_ecommerce)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ecommerce)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.activityEcommerceRecyclerview.layoutManager = GridLayoutManager(this, 2)
        adapter = CategoryAdapter {
            onCategoryClicked(it)
        }
        binding.activityEcommerceRecyclerview.adapter = adapter

        viewModel.categories.observe(this) {
            adapter.submitList(it)
        }

        binding.activityEcommerceCartBtn.setOnClickListener {
            startActivity(Intent(this, EcommerceCartActivity::class.java))
        }
    }

    fun onCategoryClicked(name: String) {
        val intent = Intent(this, CategoryProductsActivity::class.java)
        intent.putExtra("categoryName", name)
        startActivity(intent)
    }
}