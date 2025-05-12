package com.adnroidlearningkts.recyclerview.advanced.singleitemselection

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivitySingleSelBinding

class SingleSelActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleSelBinding
    private val viewModel: SingleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_single_sel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_sel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = SingleSelAdapter {
            viewModel.singleSelectEmployee(it)
        }

        binding.rvSingleSelRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.rvSingleSelRecyclerview.adapter = adapter

        viewModel.getAllEmployees()
        viewModel.employees.observe(this) {
            adapter.submitList(it)
        }

        binding.rvSingleSelBtn.setOnClickListener {
            if(viewModel.selectedEmployee.value == null) {
                Toast.makeText(this, "No Employee Selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selected Employee: ${viewModel.selectedEmployee.value?.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}