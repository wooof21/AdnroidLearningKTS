package com.adnroidlearningkts.recyclerview.advanced.cardview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.R

class SportsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sports)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.sports_card_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val sports = listOf(
            Sport(R.drawable.cardview_basketball, "Basketball"),
            Sport(R.drawable.cardview_football, "Football"),
            Sport(R.drawable.cardview_ping, "Ping Pong"),
            Sport(R.drawable.cardview_tennis, "Tennis"),
            Sport(R.drawable.cardview_volley, "Volleyball")
        )

        val adapter = SportsAdapter(sports)

        recyclerView.adapter = adapter
    }
}