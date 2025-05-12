package com.adnroidlearningkts.adapters

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R

class AdaptersActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var simpleBtn: Button
    private lateinit var customBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adapters)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //1. Initializing ListView
        listView = findViewById(R.id.adapters_listview)

        //2. Data Source
        val os = arrayOf("Windows", "Linux", "MacOS", "iOS", "Android")

        simpleBtn = findViewById(R.id.adapters_simple_btn)
        simpleBtn.setOnClickListener {
            simpleListView(os)
        }

        customBtn = findViewById(R.id.adapters_custom_btn)
        customBtn.setOnClickListener {
            customAdapter(os)
        }

    }


    private fun simpleListView(os: Array<String>) {

        //3. Adapters - creating views for each item in data source and binding data to view
        //Common Adapters: ArrayAdapter, BaseAdapter, Custom Adapters
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            os
        )

        //4. Set adapter to view
        listView.adapter = adapter
    }

    private fun customAdapter(os: Array<String>) {
        val adapter = CustomAdapter(
            this,
            os.asList()
        )

        listView.adapter = adapter
    }
}