package com.adnroidlearningkts.dependencyinjection.mvvm.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityEcommerceCartBinding
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct
import com.adnroidlearningkts.dependencyinjection.mvvm.viewmodel.EcommerceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EcommerceCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEcommerceCartBinding
    private val viewModel: EcommerceViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_ecommerce_cart)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ecommerce_cart)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.activityEcommerceCartRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter {
            viewModel.removeFromCart(it.id)
        }
        binding.activityEcommerceCartRecyclerview.adapter = adapter

        viewModel.getAllCartItems()
        viewModel.cartList.observe(this) {
            adapter.submitList(it)
        }

        binding.activityEcommerceCartClearBtn.setOnClickListener {
            viewModel.clearCart()
        }

        binding.activityEcommerceCartCheckoutBtn.setOnClickListener {
            cartCheckout()
        }

    }

    //Upload the current cart items to Ecommerce_Purchases Firestore
    private fun cartCheckout() {
        val cartItems = adapter.currentList
        if(cartItems.isEmpty()) {
            Toast.makeText(this, "Cart Is Empty", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.savePurchasesToDB(cartItems)
    }
}