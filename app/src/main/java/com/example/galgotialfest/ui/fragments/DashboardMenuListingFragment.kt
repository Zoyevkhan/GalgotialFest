package com.example.galgotialfest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.galgotialfest.databinding.FragmentDashboardMenuBinding
import com.example.galgotialfest.databinding.FragmentDashboardMenuListingBinding
import com.example.galgotialfest.ui.listners.DashboardMenuListingFragmenrListnere
import com.example.galgotialfest.viewmodels.DashboardViewModel


class DashboardMenuListingFragment(val callback:DashboardMenuListingFragmenrListnere): Fragment() {
        private var _binding: FragmentDashboardMenuListingBinding?=null
        private val binding get()=_binding!!
        val dashboardViewmodel:DashboardViewModel  by activityViewModels<DashboardViewModel>()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            super.onCreateView(inflater, container, savedInstanceState)
            _binding= FragmentDashboardMenuListingBinding.inflate(inflater, container, false)
            Log.d("zoyeb: ","F"+ dashboardViewmodel.toString())
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding.viewmodel=dashboardViewmodel
            binding.listner=callback
            binding.lifecycleOwner=viewLifecycleOwner
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding=null
        }


    }
