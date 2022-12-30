package com.example.galgotialfest.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.galgotialfest.R
import com.example.galgotialfest.databinding.ActivitySplashBinding
import com.example.galgotialfest.databinding.ActivityUserSelectionBinding
import com.example.galgotialfest.model.Darawables
import com.example.galgotialfest.model.User
import com.example.galgotialfest.model.UserTypeDrawable
import com.example.galgotialfest.ui.fragments.CustomDialogFragment
import com.example.galgotialfest.ui.listners.BindingToolbarClickListner
import com.example.galgotialfest.viewmodels.UserSelectionViewModel
import kotlin.math.log

class UserSelectionActivity : AppCompatActivity(),BindingToolbarClickListner{
    lateinit var binding: ActivityUserSelectionBinding
    lateinit var userSelectedType:SelectedUser
    lateinit var listDrawable: Darawables
    val userSelectionViewModel: UserSelectionViewModel by viewModels<UserSelectionViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_user_selection)
        setContentView(binding.root)
        initBinding()
    }

    private fun initBinding() {
        var list= mutableListOf<UserTypeDrawable>()
        list.add(setDrawable(false))
        list.add(setDrawable(false))
        listDrawable= Darawables(list)
        // inilizing the binding data
        userSelectedType=SelectedUser.NOONE
        binding.userSelectionActivity=this
        with(binding){
            name="User Selection "
            customDrawables=listDrawable

        }
    }

    fun onTeacherCardClick() {
        userSelectedType=SelectedUser.TEACHER
        changeDrawableOnClick(false)
    }
    fun navigateToNextClick() {
        if (!(userSelectedType == SelectedUser.NOONE)) {
            handleNextButtonClick()
        } else {
            Log.d("zoyeb", "navigateToNextClick: " + "noone")
        }

    }



    fun onParticipantORCoordinatorClicked() {
        userSelectedType=SelectedUser.STUDENTORCOORDINATOR
        changeDrawableOnClick(true)

    }

    private fun setDrawable(isSelected: Boolean) =
        if(isSelected)
            getUserDrawable(R.drawable.usertye_selected,Color.WHITE)
        else
            getUserDrawable(R.drawable.usertye_unselected,Color.BLACK)

    fun getUserDrawable(drawable:Int,textColor:Int)=UserTypeDrawable(drawable,textColor)

    override fun onArrowButtonClicked() {
            onBackPressed()
    }
    private fun handleNextButtonClick() {
        if(true||userSelectedType==SelectedUser.STUDENTORCOORDINATOR){
            navigateToDashBoardActivity()
        }else{
            openDialogFragment()
        }
    }

    private fun navigateToDashBoardActivity() {
        Intent(this,DashboardActivity::class.java).apply {
            startActivity(this)
            finish()
        }

    }

    private fun openDialogFragment() {
        CustomDialogFragment().show(supportFragmentManager, CustomDialogFragment.TAG)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    enum class SelectedUser{
        TEACHER,
        STUDENTORCOORDINATOR,
        NOONE
    }
    fun changeDrawableOnClick(isParticipant:Boolean){
        listDrawable.list.clear()
        listDrawable.list.add(setDrawable(isParticipant))
        listDrawable.list.add(setDrawable(!isParticipant))
        listDrawable.notifyChange()
    }

}