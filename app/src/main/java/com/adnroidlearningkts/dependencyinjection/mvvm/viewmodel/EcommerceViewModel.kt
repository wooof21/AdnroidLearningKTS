package com.adnroidlearningkts.dependencyinjection.mvvm.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct
import com.adnroidlearningkts.dependencyinjection.mvvm.model.repos.EcommerceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EcommerceViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repo: EcommerceRepo): ViewModel() {

    private val _cartList = MutableLiveData<List<CategoryProduct>>()
    val cartList: LiveData<List<CategoryProduct>> = _cartList

    val categories  = repo.getCategories()

    fun getCategoryProducts(categoryName: String) = repo.getProducts(categoryName)

    /**
     * To Display the Toast message, need to be in Main thread
     *
     * use try - catch to determine if the Room operation job is success or not
     */
    fun addProductToCart(product: CategoryProduct) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repo.addToCart(product)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "${product.title} Added To Cart", Toast.LENGTH_SHORT).show()
            }
        }catch (e: Exception) {
            Log.e("TAG", e.message.toString() )
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Add To Cart Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getAllCartItems() = viewModelScope.launch(Dispatchers.IO) {
        val cartItems = repo.getAllCartItems()
        _cartList.postValue(cartItems)
    }

    fun removeFromCart(productId: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val currentCartItems = _cartList.value ?: emptyList()
            val newCartList = currentCartItems.filter {
                it.id != productId
            }
            repo.removeFromCart(productId)
            _cartList.postValue(newCartList)
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Cart Item Remove Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun clearCart() = viewModelScope.launch(Dispatchers.IO) {
        try {
            repo.clearCart()
            _cartList.postValue(emptyList())
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Cart Clear Failed", Toast.LENGTH_SHORT).show()
                Log.e("clearCart", "${e.message}", )
            }
        }
    }

    /**
     * To write a list of documents into Firestore DB, use Batched Writes
     * https://firebase.google.com/docs/firestore/manage-data/transactions
     */
    fun savePurchasesToDB(products: List<CategoryProduct>) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repo.savePurchasesToDB(products)
            clearCart()
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Products Added To Purchases DB", Toast.LENGTH_SHORT).show()
                Log.e("clearCart", "${e.message}", )
            }
        }
    }
}