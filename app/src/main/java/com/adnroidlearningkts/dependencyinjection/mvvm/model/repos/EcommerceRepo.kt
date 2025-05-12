package com.adnroidlearningkts.dependencyinjection.mvvm.model.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adnroidlearningkts.R
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.Category
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.CategoryProduct
import com.adnroidlearningkts.dependencyinjection.mvvm.model.room.CartDAO
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.text.get

class EcommerceRepo @Inject constructor(
    private val firestore: FirebaseFirestore,
        private val cartDAO: CartDAO) {

    //Firebase: fetch Categories from Firestore
    fun getCategories(): LiveData<List<Category>> {
        var categories = MutableLiveData<List<Category>>()
        val catImages = mapOf(
            "Electronics" to R.drawable.ecommerce_electronics,
            "Jewelry" to R.drawable.ecommerce_jewelery,
            "Men" to R.drawable.ecommerce_mensclothing,
            "Women" to R.drawable.ecommerce_womenclothing,
            "Cosmetics" to R.drawable.ecommerce_cosmetics,
            "Shoes" to R.drawable.ecommerce_runningshoes,
            "Toys" to R.drawable.ecommerce_toys,
            "Tools" to R.drawable.ecommerce_tools,
            "Home" to R.drawable.ecommerce_sofa,
            "Automotive" to R.drawable.ecommerce_brake
        )

        //Fetch Categories data from Firestore
        firestore.collection("Ecommerce_Category")
            .get()  //retrieve data sync
            .addOnSuccessListener {

                val catList = it.map { doc ->
                    val name = doc.id
                    //When image resource not found, assign a default image
                    val image = catImages[name] ?: R.drawable.ic_launcher_foreground
                    //create a new instance of Category for each document
                    Category(name = name, categoryImg = image)
                }
                categories.postValue(catList)
            }

        return categories
    }

    fun getProducts(categoryName: String): LiveData<List<CategoryProduct>> {
        val products = MutableLiveData<List<CategoryProduct>>()

        //fetch Products from the document in Ecommerce_Category collection
        firestore.collection("Ecommerce_Category")
            .document(categoryName)
            //access the sub-collection under the specified document
            .collection("Products")
            .get()
            .addOnSuccessListener {
                val productList = it.map { doc ->
                    doc.toObject(CategoryProduct::class.java)
                }
                products.postValue(productList)
            }
        return products
    }


    suspend fun addToCart(item: CategoryProduct) = cartDAO.addToCart(item)

    suspend fun getAllCartItems(): List<CategoryProduct> = cartDAO.getAllCartItems()

    suspend fun removeFromCart(productId: String) = cartDAO.removeFromCart(productId)

    suspend fun clearCart() = cartDAO.clearCart()

    /**
     * To write a list of documents into Firestore DB, use Batched Writes
     * https://firebase.google.com/docs/firestore/manage-data/transactions
     */
    fun savePurchasesToDB(products: List<CategoryProduct>): Task<Void?> {
        val batch = firestore.batch()
        products.forEach {
            // Create a new document with an auto-generated ID
            //If collection path not exist, auto create the collection in Firestore
            val newDocRef = firestore.collection("Ecommerce_Purchases").document()
            batch.set(newDocRef, it)
        }
        return batch.commit()
    }

}