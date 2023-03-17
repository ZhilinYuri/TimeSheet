package com.example.timesheet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.timesheet.data.Employee
import com.example.timesheet.databinding.ItemEmployeeBinding


class EmployeeListAdapter(private val onItemEmployeeClicked: (Employee) -> Unit):
    ListAdapter<Employee, EmployeeListAdapter.EmployeeViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val current = getItem(position)

        holder.itemView.setOnClickListener {
            onItemEmployeeClicked(current)
        }
        holder.bind(current)
    }


    class EmployeeViewHolder(private var binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: Employee) {
            binding.apply {
                employeeName.text = employee.lastName
            }
        }
    }

    fun formatName(lastName: String, firstName: String, patronymic: String): String {
        return ""
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(oldEmployee: Employee, newEmployee: Employee): Boolean {
                return oldEmployee === newEmployee
            }

            override fun areContentsTheSame(oldEmployee: Employee, newEmployee: Employee): Boolean {
                return oldEmployee.lastName == newEmployee.lastName
            }
        }
    }
}