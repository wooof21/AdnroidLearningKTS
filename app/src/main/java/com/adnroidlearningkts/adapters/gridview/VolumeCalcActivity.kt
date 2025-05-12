package com.adnroidlearningkts.adapters.gridview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.adnroidlearningkts.R
import kotlin.math.pow

class VolumeCalcActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var result: TextView
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var et3: EditText
    private lateinit var clac: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_volume_calc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()

        val bundle = intent.extras
        val shapeName = bundle?.getString("shapeName")
        updateView(shapeName)

        clac.setOnClickListener {
            if(et1.text.isBlank() || (et2.isVisible && et2.text.isBlank()) || et3.isVisible && et3.text.isBlank()) {
                Toast.makeText(this, "Please Enter All Shape Specs", Toast.LENGTH_SHORT).show()
            }
            else if(!et1.text.isDigitsOnly() || !et2.text.isDigitsOnly() || !et3.text.isDigitsOnly()) {
                Toast.makeText(this, "Please Enter Numbers Only", Toast.LENGTH_SHORT).show()
            }
            else {
                val volume: Double
                when(shapeName) {
                    "Sphere" -> {
                        val s_radius: Double = et1.text.toString().toDouble()
                        volume = (4/3) * Math.PI * s_radius.pow(3.0)
                        result.text = "Sphere Volume: ${String.format("%.2f", volume)} m^3"
                    }
                    "Cylinder" -> {
                        val c_radius: Double = et1.text.toString().toDouble()
                        val c_height: Double = et2.text.toString().toDouble()
                        volume = Math.PI * c_radius.pow(2.0) * c_height
                        result.text = "Cylinder Volume: ${String.format("%.2f", volume)} m^3"
                    }
                    "Cube" -> {
                        val c_lenght: Double = et1.text.toString().toDouble()
                        volume = c_lenght.pow(3.0)
                        result.text = "Cube Volume: ${String.format("%.2f", volume)} m^3"
                    }
                    "Prism" -> {
                        val p_length = et1.text.toString().toDouble()
                        val p_height = et2.text.toString().toDouble()
                        val p_width = et3.text.toString().toDouble()
                        volume = p_length * p_height * p_width
                        result.text = "Prism Volume: ${String.format("%.2f", volume)} m^3"
                    }
                }
            }
        }
    }

    private fun initView() {
        title = findViewById(R.id.vol_calc_result_title)
        result = findViewById(R.id.vol_calc_result)
        et1 = findViewById(R.id.vol_calc_result_edidtext1)
        et2 = findViewById(R.id.vol_calc_result_edidtext2)
        et2.visibility = View.GONE
        et3 = findViewById(R.id.vol_calc_result_edidtext3)
        et3.visibility = View.GONE
        clac = findViewById(R.id.vol_calc_result_btn)
    }

    private fun updateView(shapeName: String?) {
        when (shapeName) {
            "Sphere" -> {
                title.text = "Sphere Volume"
                et1.hint = "Please Enter The Radius"
                et2.visibility = View.GONE
                et3.visibility = View.GONE
            }
            "Cylinder" -> {
                title.text = "Cylinder Volume"
                et1.hint = "Please Enter The Radius"
                et2.visibility = View.VISIBLE
                et2.hint = "Please Enter The Height"
                et3.visibility = View.GONE
            }
            "Cube" -> {
                title.text = "Cube Volume"
                et1.hint = "Please Enter The Length of Side"
                et2.visibility = View.GONE
                et3.visibility = View.GONE
            }
            "Prism" -> {
                title.text = "Prism Volume"
                et1.hint = "Please Enter The Length"
                et2.visibility = View.VISIBLE
                et2.hint = "Please Enter The Height"
                et3.visibility = View.VISIBLE
                et3.hint = "Please Enter The Width"
            }
            null -> finish()
        }
    }
}