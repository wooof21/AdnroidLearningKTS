package com.adnroidlearningkts.dependencyinjection.mvvm.view

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityProductDetailsBinding
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct
import com.adnroidlearningkts.dependencyinjection.mvvm.viewmodel.EcommerceViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val viewModel: EcommerceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_product_details)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the Parcelable object from the Intent extras
        val product: CategoryProduct? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Use getParcelableExtra with the class for Android 13+
            intent.getParcelableExtra("categoryProduct", CategoryProduct::class.java)
        } else {
            // Use the older getParcelableExtra method for older Android versions
            @Suppress("DEPRECATION") // Suppress the deprecation warning for older API levels
            intent.getParcelableExtra("categoryProduct")
        }

        if(product == null) {
            Toast.makeText(this, "Product Details Unavailable. ", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.activityProductDetailsTitle.text = product!!.title
        binding.activityProductDetailsPrice.text = product.price.toString()
        Glide.with(this)
            .load(product.imageUrl)
            .into(binding.activityProductDetailsImage)

        binding.activityProductDetailsCart.setOnClickListener {
            viewModel.addProductToCart(product)
        }

    }

}