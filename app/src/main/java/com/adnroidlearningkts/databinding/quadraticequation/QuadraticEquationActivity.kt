package com.adnroidlearningkts.databinding.quadraticequation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityQuadraticEquationBinding
import com.adnroidlearningkts.databinding.quadraticequation.model.Equation

class QuadraticEquationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuadraticEquationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quadratic_equation)
//        setContentView(R.layout.activity_quadratic_equation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.equation = Equation(binding.quadraticEdittext1.text.toString(),
                                    binding.quadraticEdittext2.text.toString(),
                                    binding.quadraticEdittext3.text.toString())
    }
}