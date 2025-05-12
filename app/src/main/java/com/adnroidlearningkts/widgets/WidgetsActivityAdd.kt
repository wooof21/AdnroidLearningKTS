package com.adnroidlearningkts.widgets

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R

class WidgetsActivityAdd : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var progressBar: ProgressBar
    private lateinit var increase: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_widgets_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initDatePicker()

        initProgressBar()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initDatePicker() {
        datePicker = findViewById(R.id.widgets_date_picker)

        /**
         * datePicker: view - Date Picker itself
         * i: year
         * i2: month of the year, range 0 - 11
         * i3: day of the month, start with 1
         */
        datePicker.setOnDateChangedListener { datePicker, i, i2, i3 ->
            Toast.makeText(this, "Year: $i - Month: ${i2+1} - Day: $i3", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initProgressBar() {
        progressBar = findViewById(R.id.widgets_progress_bar)
        increase = findViewById(R.id.widgets_progress_add_btn)

        /**
         * Set progress for determinate ProgressBar
         */
        var progress = 0
        increase.setOnClickListener {
            progress += 10
            progressBar.progress = progress
        }
    }
}