package com.adnroidlearningkts.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.R
import com.adnroidlearningkts.recyclerview.model.Grocery

class GroceryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grocery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.grocery_recycler_view)
        /**
         * layoutManager: responsible for positioning and measuring items in RecyclerView
         *
         * LinearLayoutManager: Android build-in layout manager, it arranges items in a linear
         * fashion, either horizontally or vertically, depending on the orientation specified
         */
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //Data source
        val groceries: List<Grocery> = listOf(
            Grocery(R.drawable.grocery_fruit, "Fruits", "Fresh Fruits from the Garden."),
            Grocery(R.drawable.grocery_vegitables, "Vegetables", "Fresh Vegetables."),
            Grocery(R.drawable.grocery_bread, "Bakery", "Bread, Wheat and Beans."),
            Grocery(R.drawable.grocery_beverage, "Beverage", "Juice, Tea, Coffee and Soda."),
            Grocery(R.drawable.grocery_milk, "Milk", "Milk, Shakes and Yogurt."),
            Grocery(R.drawable.grocery_popcorn, "Snacks", "Pop Corn, Donut and Drinks.")
        )

        val adapter = GroceryAdapter(groceries)

        recyclerView.adapter = adapter
    }
}