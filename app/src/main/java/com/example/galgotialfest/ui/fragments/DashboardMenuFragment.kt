package com.example.galgotialfest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.galgotialfest.databinding.FragmentDashboardMenuBinding
import com.example.galgotialfest.ui.listners.DashboardFragmentListner
import com.example.galgotialfest.viewmodels.DashboardViewModel
import com.example.galgotialfest.viewmodels.SplashViewModel

class DashboardMenuFragment():Fragment() {
    private var _binding: FragmentDashboardMenuBinding?=null
    private val binding get() = _binding!!
    private lateinit var callBack: DashboardFragmentListner
    val dashboardViewmodel: DashboardViewModel by activityViewModels<DashboardViewModel>()

    constructor(callBack: DashboardFragmentListner) : this() {
        this.callBack = callBack
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding= FragmentDashboardMenuBinding.inflate(inflater, container, false)
        Log.d("zoyeb: ","F"+ dashboardViewmodel.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataAndSetBinding()
    }

    private fun loadDataAndSetBinding() {
        dashboardViewmodel.loadMenus()
        binding.dashboardVM=dashboardViewmodel
        binding.listener=callBack
        binding.lifecycleOwner=viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}