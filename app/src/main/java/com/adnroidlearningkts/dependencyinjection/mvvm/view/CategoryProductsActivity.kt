package com.adnroidlearningkts.dependencyinjection.mvvm.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityCategoryProductsBinding
import com.adnroidlearningkts.databinding.ActivityProductDetailsBinding
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct
import com.adnroidlearningkts.dependencyinjection.mvvm.viewmodel.EcommerceViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.toLong

@AndroidEntryPoint
class CategoryProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryProductsBinding
    private lateinit var adapter: CategoryProductAdapter
    private val viewModel: EcommerceViewModel by viewModels()


    /**
     * To have the Product Details View expand from click event instead of start the Details activity
     */
//    private lateinit var productDetailsContainer: View // The FrameLayout container for details
//    // Animation duration
//    private val shortAnimationDuration: Int by lazy {
//        resources.getInteger(android.R.integer.config_shortAnimTime)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_category_products)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_products)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.activityCategoryProductsRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = CategoryProductAdapter {
            onProductClicked(it)
        }
        binding.activityCategoryProductsRecyclerview.adapter = adapter


        val categoryName: String = intent.getStringExtra("categoryName") ?: ""
        viewModel.getCategoryProducts(categoryName).observe(this) {
            adapter.submitList(it)
        }

//        // Find the product details container and bind the included layout
//        productDetailsContainer = binding.productDetailsContainer
////        productDetailsBinding = ActivityProductDetailsBinding.inflate(layoutInflater)
//
//        // Optional: Set up a click listener to close the details when clicking the container
//        productDetailsContainer.setOnClickListener {
//            hideProductDetails()
//        }
//
//        // Optional: Handle back button press to close the details
//        onBackPressedDispatcher.addCallback(this) {
//            if (productDetailsContainer.visibility == View.VISIBLE) {
//                hideProductDetails()
//            } else {
//                isEnabled = false // Allow default back behavior if details are not shown
//                onBackPressedDispatcher.onBackPressed()
//            }
//        }
    }

    fun onProductClicked(product: CategoryProduct) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("categoryProduct", product)
        startActivity(intent)

//        // Populate the product details layout with the clicked product's data
//        populateProductDetails(product)
//        // Show and animate the product details view
//        showProductDetails()
    }

//    private fun populateProductDetails(product: CategoryProduct) {
//        // Use the details binding to set the data
//        Log.i("TAG", binding.productDetailsLayout.activityProductDetailsTitle.text.toString())
//        binding.productDetailsLayout.activityProductDetailsTitle.text = product.title
//        binding.productDetailsLayout.activityProductDetailsPrice.text = product.price.toString()
//        Glide.with(this)
//            .load(product.imageUrl)
//            .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder
//            .error(R.drawable.ic_launcher_foreground) // Optional error image
//            .into(binding.productDetailsLayout.activityProductDetailsImage)
//
//        // Update other views in the details layout as needed
//    }

//    private fun showProductDetails() {
//        // Set the content view to 0% opacity but visible, so that it is visible
//        // (but fully transparent) during the animation.
//        productDetailsContainer.alpha = 0f
//        productDetailsContainer.visibility = View.VISIBLE
//
//        // Animate the content view to 100% opacity, and clear any animation
//        // listener set on the view.
//        productDetailsContainer.animate()
//            .alpha(1f) // Animate alpha from 0 to 1
//            .setDuration(shortAnimationDuration.toLong()) // Set the animation duration
//            .setListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator) {
//                    // Optional: Actions to perform after the animation ends
//                    // For example, ensure clickability is active
//                }
//            })
//            .start() // Start the animation
//    }
//
//    // Hides the product details container with a fade-out animation
//    private fun hideProductDetails() {
//        // Animate the content view to 0% opacity. After the animation ends,
//        // set its visibility to GONE as an optimization step (it won't
//        // participate in layout passes when it's gone).
//        productDetailsContainer.animate()
//            .alpha(0f) // Animate alpha from current (1f) to 0 (fade out)
//            .setDuration(shortAnimationDuration.toLong()) // Set the animation duration
//            .setListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator) {
//                    productDetailsContainer.visibility = View.GONE // Hide the view after animation
//                }
//            })
//            .start() // Start the animation
//    }
}