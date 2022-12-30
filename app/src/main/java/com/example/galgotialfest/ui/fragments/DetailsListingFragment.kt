package com.example.galgotialfest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.galgotialfest.databinding.DetailsListingFragmentBinding
import com.example.galgotialfest.databinding.FragmentDashboardMenuBinding.inflate
import com.example.galgotialfest.databinding.FragmentDetailsBinding
import com.example.galgotialfest.enums.DetailsFragmentCLickedMenu
import com.example.galgotialfest.enums.UserType
import com.example.galgotialfest.model.User
import com.example.galgotialfest.ui.activity.DetailsActivity
import com.example.galgotialfest.ui.listners.FragmentDetailsListingListener
import com.example.galgotialfest.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


class DetailsListingFragment (): Fragment() {
    private var _binding:DetailsListingFragmentBinding ?=null
    private val binding get()=_binding!!
    val detailsViewmodel: DetailsViewModel by activityViewModels<DetailsViewModel>()
    var userType:Int=0

    companion object{
     fun getInstance(userType: Int) = DetailsListingFragment().apply {
         arguments = Bundle().apply {
             putInt("user_type", userType)
         }
     }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        userType = arguments?.getInt("user_type") ?: 0
        _binding = DetailsListingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.usertype=userType
        binding.detailsViewmodel=detailsViewmodel
        binding.callback=(activity as DetailsActivity).HandleDetailsListingClicks()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}
