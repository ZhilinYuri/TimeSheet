package com.example.timesheet.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.timesheet.R
import com.example.timesheet.databinding.FragmentAddEmployeeBinding
import com.example.timesheet.databinding.FragmentEmployeeListBinding
import com.example.timesheet.viewModel.TimeSheetViewModel

class AddEmployeeFragment : Fragment() {

    private var _binding: FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TimeSheetViewModel by viewModels { TimeSheetViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
            addNewEmployee()
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

    fun clearEdittext(){
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