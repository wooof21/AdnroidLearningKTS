package com.adnroidlearningkts.recyclerview.advanced.multiitemselection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.RvSingleSelItemBinding
import com.adnroidlearningkts.recyclerview.advanced.singleitemselection.Employee

class MultiSelAdapter(private val onMultiItemSelection: (Int, Employee) -> Unit): ListAdapter<Employee, MultiSelAdapter.ViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(
                oldItem: Employee,
                newItem: Employee
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: Employee,
                newItem: Employee
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = RvSingleSelItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: RvSingleSelItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: Employee) {
            binding.advRvSingleSelEmpName.text = employee.name
            if(employee.isChecked) {
                binding.advRvSingleSelImageview.visibility = View.VISIBLE
            } else {
                binding.advRvSingleSelImageview.visibility = View.GONE
            }
            binding.advRvSingleSelLayout.setOnClickListener {
                onMultiItemSelection(adapterPosition, employee)
            }
        }
    }
}