package com.adnroidlearningkts.adapters.gridview

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import com.adnroidlearningkts.adapters.gridview.model.Shape

class VolumeGridActivity : AppCompatActivity() {

    private lateinit var gridView: GridView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_volume_grid)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gridView = findViewById(R.id.vol_calc_grdiview)

        val shapes = listOf(
            Shape(R.drawable.vol_calc_sphere, "Sphere"),
            Shape(R.drawable.vol_calc_cylinder, "Cylinder"),
            Shape(R.drawable.vol_calc_cube, "Cube"),
            Shape(R.drawable.vol_calc_prism, "Prism")
        )

        val adapter = VolCalcAdapter(this, shapes)

        gridView.adapter = adapter

        /**
         * handle item click event
         *
         * 1. adapterView: the adapter view
         * 4. l: ID of clicked view
         * 3. i: position
         * 
         *  `_` : a convention to indicate the parameter is not going to be used in the lambda expression
         */
        gridView.setOnItemClickListener { adapterView, view, i, _ ->
            //adapter.getItem(i)
            val shape = adapterView.adapter.getItem(i) as Shape
            val intent = Intent(this, VolumeCalcActivity::class.java)
            intent.putExtra("shapeName", shape.shapeName)
            startActivity(intent)
        }
    }
}