package com.example.timesheet.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timesheet.R
import com.example.timesheet.data.Employee
import com.example.timesheet.databinding.FragmentAddEmployeeBinding
import com.example.timesheet.databinding.FragmentEmployeeDetailBinding
import com.example.timesheet.viewModel.TimeSheetViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EmployeeDetailFragment : Fragment() {

    private val viewModel: TimeSheetViewModel by viewModels { TimeSheetViewModel.Factory }
    private val navigationArgs: EmployeeDetailFragmentArgs by navArgs()
    private var _binding: FragmentEmployeeDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var employee: Employee

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = navigationArgs.employeeId
        viewModel.retrieveEmployee(id).observe(this.viewLifecycleOwner) { selectedEmployee ->
            employee = selectedEmployee
            bind(employee)
        }
    }
    private fun bind(employee: Employee) {
        binding.apply {
            lastName.text = employee.lastName
            firstName.text = employee.firstName
            patronymic.text = employee.patronymic
            position.text = employee.position
            buttonDelete.setOnClickListener { showConfirmationDialog() }
            buttonChange.setOnClickListener { editEmployee() }
        }
    }

    private fun editEmployee() {
        val action = EmployeeDetailFragmentDirections.actionEmployeeDetailFragmentToAddEmployeeFragment(
            getString(R.string.edit_fragment_title),
            employee.id
        )
        this.findNavController().navigate(action)
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteEmployee()
            }
            .show()
    }
    private fun deleteEmployee() {
        viewModel.deleteEmployee(employee)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}