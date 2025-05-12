package com.adnroidlearningkts.recyclerview.advanced.multiitemselection

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
import com.adnroidlearningkts.databinding.ActivityMultiSelBinding

class MultiSelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultiSelBinding
    private val viewModel: MultiViewModel by viewModels()
    private lateinit var adapter: MultiSelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_multi_sel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_multi_sel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.advRvMultiSelRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MultiSelAdapter {
            position, employee -> viewModel.multiSelectEmployees(position, employee)
        }
        binding.advRvMultiSelRecyclerview.adapter = adapter

        viewModel.getAllEmployees()
        viewModel.employees.observe(this) {
            adapter.submitList(it)
        }

        binding.advRvMultiSelBtn.setOnClickListener {
            val selectedEmpMap = viewModel.selectedEmployees.value
            if(selectedEmpMap == null) {
                Toast.makeText(this, "No Employee Selected", Toast.LENGTH_SHORT).show()
            } else {
                val sb: StringBuilder = StringBuilder()
                val entries = selectedEmpMap.entries.iterator()
                while (entries.hasNext()) {
                    val emp = entries.next()
                    sb.append("Position: ${emp.key} - Name: ${emp.value.name} - isChecked: ${emp.value.isChecked}")
                    sb.append("\n")
                }
                binding.advRvMultiSelTv.text = sb.toString()
            }
        }
    }
}