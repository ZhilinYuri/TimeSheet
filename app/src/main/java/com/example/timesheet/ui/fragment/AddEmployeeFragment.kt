package com.example.timesheet.ui.fragment

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timesheet.R
import com.example.timesheet.data.Employee
import com.example.timesheet.databinding.FragmentAddEmployeeBinding
import com.example.timesheet.databinding.FragmentEmployeeListBinding
import com.example.timesheet.viewModel.TimeSheetViewModel

class AddEmployeeFragment : Fragment() {

    private val navigationArgs: AddEmployeeFragmentArgs by navArgs()
    private var _binding: FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TimeSheetViewModel by viewModels { TimeSheetViewModel.Factory }
    private lateinit var employee: Employee


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.employeeId
        if (id > 0) {
            viewModel.retrieveEmployee(id).observe(this.viewLifecycleOwner) { selectedEmployee ->
                employee = selectedEmployee
                bind(employee)
            }
        } else {
            binding.apply {
                buttonSave.animate().apply {
                    scaleX(1.1f)
                    scaleY(1.1f)
                    alpha(1f)
                    duration = 1000
                    interpolator= AccelerateDecelerateInterpolator()
                }.start()
                buttonSave.setOnClickListener {
                    addNewEmployee()
                }
            }
        }
    }
    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
           binding.lastName.text.toString(),
           binding.firstName.text.toString(),
           binding.patronymic.text.toString(),
           binding.position.text.toString(),

            )
    }
    private fun addNewEmployee() {
        if (isEntryValid()) {
            viewModel.addNewBook(
                binding.lastName.text.toString(),
                binding.firstName.text.toString(),
                binding.patronymic.text.toString(),
                binding.position.text.toString(),
            )
            clearEdittext()
        }
    }
    private fun bind(employee: Employee) {
        binding.apply {
            lastName.setText(employee.lastName, TextView.BufferType.SPANNABLE)
            firstName.setText(employee.firstName, TextView.BufferType.SPANNABLE)
            patronymic.setText(employee.patronymic, TextView.BufferType.SPANNABLE)
            position.setText(employee.position, TextView.BufferType.SPANNABLE)
            buttonSave.animate().apply {
                scaleX(1.1f)
                scaleY(1.1f)
                alpha(1f)
                duration = 1000
                interpolator= AccelerateDecelerateInterpolator()
            }.start()
            buttonSave.setOnClickListener { updateEmployee() }
        }
    }

    private fun updateEmployee() {
        if (isEntryValid()) {
            viewModel.updateItem(
                this.navigationArgs.employeeId,
                this.binding.lastName.text.toString(),
                this.binding.firstName.text.toString(),
                this.binding.patronymic.text.toString(),
                this.binding.position.text.toString()
            )
            val action = AddEmployeeFragmentDirections.actionAddEmployeeFragmentToEmployeeListFragment()
            findNavController().navigate(action)
        }
    }

    private fun clearEdittext(){
        binding.lastName.text?.clear()
        binding.lastName.clearFocus()
        binding.firstName.text?.clear()
        binding.firstName.clearFocus()
        binding.patronymic.text?.clear()
        binding.patronymic.clearFocus()
        binding.position.text?.clear()
        binding.position.clearFocus()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }

}