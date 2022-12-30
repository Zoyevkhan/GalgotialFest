package com.example.galgotialfest.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.galgotialfest.databinding.AddFragmentLayoutBinding
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.util.LoadingState
import com.example.galgotialfest.viewmodels.DashboardViewModel
import com.example.galgotialfest.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


class AddUserFragment(): Fragment() {
    private var _binding: AddFragmentLayoutBinding?=null
    private val binding get()=_binding!!
    var userType:Int=0
    lateinit var gameCat:CommonModel
    val detailsViewmodel: DetailsViewModel by activityViewModels<DetailsViewModel>()

    companion object{
        fun getInstance(userType:Int,data:CommonModel)=AddUserFragment().apply {
            arguments=Bundle().apply {
                putInt("user_type",userType)
                putSerializable("game_type",data)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding= AddFragmentLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userType=arguments?.getInt("user_type")?:0
        gameCat=arguments?.getSerializable("game_type") as CommonModel
        binding.viewmodel=detailsViewmodel
        binding.userType=userType
        binding.metadata=gameCat
        setObservers()
    }

    private fun setObservers() {
        detailsViewmodel.addFragmentMessage.observe(viewLifecycleOwner, Observer {
            it?.let{loadingState->
                loadingState?.let {
                    when(it){
                        is LoadingState.isLoading -> binding.progressbar.visibility=View.VISIBLE
                        is LoadingState.Sucess->Toast.makeText(activity,it.message?:" ",Toast.LENGTH_LONG).show()
                        is LoadingState.Fail->Toast.makeText(activity,it.message?:" ",Toast.LENGTH_LONG).show()
                    }
                }
                detailsViewmodel.addFragmentMessage.value=null
            }
        })
        detailsViewmodel.game.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("kokakoka: 1", it.toString())
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        detailsViewmodel.refershUserdata()
        _binding=null
    }
}
