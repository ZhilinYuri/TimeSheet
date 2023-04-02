package com.example.timesheet.ui.fragment

import android.graphics.Color.alpha
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.timesheet.R
import com.example.timesheet.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            buttonEmployees.animate().apply {
                translationX(-500f)
                scaleX(1.1f)
                scaleY(1.1f)
                alpha(1f)
                duration=1000
                interpolator= AccelerateDecelerateInterpolator()
            }.start()
            buttonEmployees.setOnClickListener {
               val action = StartFragmentDirections.actionStartFragmentToEmployeeListFragment()
               findNavController().navigate(action)
           }
            buttonTimeSheet.animate().apply {
                translationX(-500f)
                scaleX(1.1f)
                scaleY(1.1f)
                alpha(1f)
                duration=600
                interpolator= AccelerateDecelerateInterpolator()
            }.start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}