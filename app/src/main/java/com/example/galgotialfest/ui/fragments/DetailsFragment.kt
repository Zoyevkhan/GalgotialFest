package com.example.galgotialfest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentHostCallback
import androidx.fragment.app.activityViewModels
import com.example.galgotialfest.databinding.FragmentDetailsBinding
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.ui.activity.DetailsActivity
import com.example.galgotialfest.ui.listners.DetailsFragmentListener
import com.example.galgotialfest.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment(): Fragment() {
    private var _binding:FragmentDetailsBinding ?=null
    private val binding get()=_binding!!
    private lateinit var callback: DetailsFragmentListener
    /*val detailsViewmodel: DashboardViewModel by activityViewModels<DashboardViewModel>()*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding= FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.callback=(activity as DetailsActivity).HandleDetailsItemClicks()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}