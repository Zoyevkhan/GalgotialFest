package com.example.galgotialfest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.galgotialfest.R
import com.example.galgotialfest.binding.bindingLayoutModel.ToolbarBinding
import com.example.galgotialfest.databinding.ActivityDetailsBinding
import com.example.galgotialfest.enums.*
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.model.DetailsUser
import com.example.galgotialfest.ui.fragments.*
import com.example.galgotialfest.ui.listners.BindingToolbarClickListner
import com.example.galgotialfest.ui.listners.DetailsFragmentListener
import com.example.galgotialfest.ui.listners.FragmentDetailsListingListener
import com.example.galgotialfest.util.BackstackFragmentName
import com.example.galgotialfest.util.Dataprovider
import com.example.galgotialfest.viewmodels.DetailsViewModel
import com.example.galgotialfest.viewmodels.UserSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity(),BindingToolbarClickListner {
    lateinit var binding: ActivityDetailsBinding
    lateinit var toolbarBinding: ToolbarBinding
    lateinit var userData: CommonModel
    val detailsViewModel: DetailsViewModel by viewModels<DetailsViewModel>()
    lateinit var userType: DetailsFragmentCLickedMenu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_details)
        val window: Window = getWindow()
        Dataprovider.setParentCatMap()
        Dataprovider.setSubCatMap()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.darker_gray)
        if(intent.hasExtra("Details_Data")){
           /* userData= CommonModel("Musical Chair", R.drawable.nukkad_natak,DashboardMenusCat.FUNGAME.ordinal,GameType.FUNGAME,SubCategory.MUSICAL_CHAIR)*/
           userData=intent.getSerializableExtra("Details_Data") as CommonModel
        }
        initialiseViews()
        loadGameData()
    }

    private fun loadGameData() {
        detailsViewModel.loadData(userData.parentCat!!,userData.subCategory!!)
    }

    private fun initialiseViews() {
        if(supportFragmentManager.backStackEntryCount==0)
        addFragment(getDetailsFragment())
        toolbarBinding=ToolbarBinding(
           userData?.name?:"",
           true,
           this
        )
        if(this::userData.isInitialized) {
            binding.itemData = userData
            binding.toolbarData = toolbarBinding
        }
    }
    private fun handleDetailsItemClicked(itemType: DetailsFragmentCLickedMenu) {
        userType=itemType
        setToolbar(getTitleOfToolbar(itemType),true)
        when (itemType) {
            /*DetailsFragmentCLickedMenu.CLICK_HERE->replaceFragment(
              //  getDetailsFragment()
            )*/
            else -> replaceFragment(getDetailsListingFragment(itemType), "InstructionFragment", true)
        }
    }

    private fun getTitleOfToolbar(itemType: DetailsFragmentCLickedMenu?=null,position:Int=-1): String {
       var itemTypeIndex=itemType?.ordinal?:position
        if(itemTypeIndex == DetailsFragmentCLickedMenu.CLICK_HERE.ordinal){
            return "Instruction"
        }else if(itemTypeIndex == DetailsFragmentCLickedMenu.PARTICIPATE.ordinal){
            return "Participate"
        }else if(itemTypeIndex == DetailsFragmentCLickedMenu.TEACHER_COORDINATOR.ordinal){
            return "Teacher Coordinators"
        }else if(itemTypeIndex == DetailsFragmentCLickedMenu.STUDENT_COORDINATOR.ordinal){
            return "Student Coordinators"
        }else if(itemTypeIndex == DetailsFragmentCLickedMenu.MATCHES.ordinal){
            return "Matches"
        }else{
            return " "
        }
    }

    private fun setToolbar(title:String,arrawVisibility:Boolean) {
            toolbarBinding.toolbarTitle = title
            toolbarBinding.isBackButtonVisible = arrawVisibility
            toolbarBinding.notifyChange()

    }


    private fun getDetailsListingFragment(itemType: DetailsFragmentCLickedMenu): Fragment {
        return DetailsListingFragment.getInstance(itemType.ordinal)

    }
    private fun getDetailsFragment(): Fragment {
        return DetailsFragment()
    }
    inner class HandleDetailsItemClicks :DetailsFragmentListener{
        override fun onItemClicked(itemType: DetailsFragmentCLickedMenu) {
            handleDetailsItemClicked(itemType)
        }

    }
    inner class HandleDetailsListingClicks:FragmentDetailsListingListener{
        override fun onItemAddClicked(userType: Int) {
            setToolbar(getTitleOfToolbar(position = userType),true)
           replaceFragment(
               getAddFragment(userType),
               "Add",
           true
           )
        }

        override fun onUserMakeWinnerClicked(user: DetailsUser):Boolean {
            Log.d("winner", "onUserMakeWinnerClicked: ")
            return true
        }



    }

    private fun getAddFragment(userType: Int): Fragment {
        return AddUserFragment.getInstance(userType,userData)

    }

    override fun onArrowButtonClicked() {
        onBackPressed()
    }
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.detailsContainer.id, fragment)
            .setCustomAnimations(
                R.anim.fragment_slide_left,
                R.anim.fragment_slide_right,
                R.anim.fragment_slide_left,
                R.anim.fragment_slide_right
            )
            .addToBackStack(BackstackFragmentName.DetailsFragment)
            .commit()
    }
    private fun replaceFragment(
        fragment: Fragment,
        tag: String,
        isBackstack: Boolean
    ) {
        /*setToolbar(true,menu.name)*/
        var transaction = supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fragment_slide_left,
                R.anim.fragment_slide_right,
                R.anim.fragment_slide_left,
                R.anim.fragment_slide_right
            )
            .replace(binding.detailsContainer.id, fragment)
        transaction = if (isBackstack) transaction.addToBackStack(tag) else transaction
        transaction.commit()
    }


    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(binding.detailsContainer.id)
        if(fragment is AddUserFragment){
            setToolbar(getTitleOfToolbar(userType),true)
        }
        if (fragment is DetailsFragment) {
           /* supportFragmentManager.popBackStack()*/
           finish()
        } else if (fragment is DetailsListingFragment) {
            supportFragmentManager.popBackStack()
            setToolbar(userData.name, true)
        } else {
            super.onBackPressed()
        }
    }

}