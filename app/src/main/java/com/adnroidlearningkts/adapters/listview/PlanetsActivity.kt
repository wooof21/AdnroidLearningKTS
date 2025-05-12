package com.adnroidlearningkts.adapters.listview

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import com.adnroidlearningkts.adapters.listview.model.Planet

class PlanetsActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_planets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView = findViewById(R.id.planets_list_view)

        val planets = listOf(
            Planet(R.drawable.earth, "Earth", "1 Moon"),
            Planet(R.drawable.mercury, "Mercury", "0 Moon"),
            Planet(R.drawable.venus, "Venus", "0 Moon"),
            Planet(R.drawable.mars, "Mars", "2 Moon"),
            Planet(R.drawable.jupiter, "Jupiter", "79 Moon"),
            Planet(R.drawable.saturn, "Saturn", "83 Moon"),
            Planet(R.drawable.uranus, "Uranus", "27 Moon"),
            Planet(R.drawable.neptune, "Neptune", "14 Moon")
        )

        val adapter = PlanetsAdapter(this, planets)

        listView.adapter = adapter
    }
}