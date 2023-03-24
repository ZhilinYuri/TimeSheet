package com.example.timesheet.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timesheet.R
import com.example.timesheet.adapter.EmployeeListAdapter
import com.example.timesheet.databinding.FragmentEmployeeListBinding
import com.example.timesheet.viewModel.TimeSheetViewModel

class EmployeeListFragment : Fragment() {

    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TimeSheetViewModel by viewModels { TimeSheetViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = EmployeeListAdapter {

            val action = EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFragment(it.id)
            this.findNavController().navigate(action)

        }
        binding.recyclerView.adapter = adapter
        viewModel.allEmployees.observe(this.viewLifecycleOwner) { books ->
            books.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.floatingActionButton.setOnClickListener {
            val action = EmployeeListFragmentDirections.actionEmployeeListFragmentToAddEmployeeFragment(
                getString(R.string.add_employee)
            )
            this.findNavController().navigate(action)
        }
    }
}