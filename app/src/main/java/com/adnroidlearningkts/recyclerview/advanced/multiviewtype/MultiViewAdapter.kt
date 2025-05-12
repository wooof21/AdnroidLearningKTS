package com.adnroidlearningkts.recyclerview.advanced.multiviewtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.databinding.RvMultiTypeEmailBinding
import com.adnroidlearningkts.databinding.RvMultiTypePhoneBinding


private const val TYPE_PHONE = 1
private const val TYPE_EMAIL = 2


class MultiViewAdapter(private val employees: List<EmployeeMultiType>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val phoneBinding = RvMultiTypePhoneBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        val emailBinding: RvMultiTypeEmailBinding = RvMultiTypeEmailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        if(viewType == TYPE_PHONE) {
            return ViewHolderPhone(phoneBinding)
        }
        return ViewHolderEmail(emailBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == TYPE_PHONE) {
            (holder as ViewHolderPhone).bind(employees[position] as EmployeeWithPhone)
        } else {
            (holder as ViewHolderEmail).bind(employees[position] as EmployeeWithEmail)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val employee =employees[position]
        return when (employee) {
            is EmployeeWithEmail -> TYPE_EMAIL
            else -> TYPE_PHONE
        }
    }

    override fun getItemCount(): Int = employees.size


    inner class ViewHolderPhone(val binding: RvMultiTypePhoneBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeeWithPhone) {
            binding.employeePhone = employee
        }
    }

    inner class ViewHolderEmail(val binding: RvMultiTypeEmailBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeeWithEmail) {
            binding.employeeEmail = employee
        }
    }
}