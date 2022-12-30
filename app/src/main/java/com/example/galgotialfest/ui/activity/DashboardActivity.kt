package com.example.galgotialfest.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galgotialfest.R
import com.example.galgotialfest.binding.bindingLayoutModel.ToolbarBinding
import com.example.galgotialfest.database.PreferenceManager
import com.example.galgotialfest.databinding.ActivityDashboardBinding
import com.example.galgotialfest.databinding.DashboardItemBinding
import com.example.galgotialfest.enums.DashboardMenusCat
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.ui.adapters.DashboardAdapter
import com.example.galgotialfest.ui.fragments.CustomDialogFragment.Companion.TAG
import com.example.galgotialfest.ui.fragments.DashboardMenuFragment
import com.example.galgotialfest.ui.fragments.DashboardMenuListingFragment
import com.example.galgotialfest.ui.listners.BindingToolbarClickListner
import com.example.galgotialfest.ui.listners.DashboardFragmentListner
import com.example.galgotialfest.ui.listners.DashboardMenuListingFragmenrListnere
import com.example.galgotialfest.util.BackstackFragmentName
import com.example.galgotialfest.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity(), BindingToolbarClickListner {
    lateinit var binding: ActivityDashboardBinding
    lateinit var toolbarBinding: ToolbarBinding

    @Inject
    lateinit var preferenceManager: PreferenceManager
    val dashboardViewModel: DashboardViewModel by viewModels<DashboardViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        val window: Window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.darker_gray)
        preferenceManager.saveString("data", "haina")
        initilizeViews()
        Log.d("khan", dashboardViewModel.toString())

    }

    private fun initilizeViews() {
        toolbarBinding = ToolbarBinding(
            "Home",
            true, this
        )
        binding.toolbarData = toolbarBinding
        /*   Log.d("zoyeb", "A"+dashboardVM.toString())*/
        if (supportFragmentManager.backStackEntryCount == 0) {
            Log.d("zoyeb", "initilizeViews: ")
            addFragment(getDashboardFragment())

        }

    }

    private fun getDashboardFragment(): Fragment {
        setToolbar(false, "Dashboard")
        var frag = DashboardMenuFragment(object : DashboardFragmentListner {
            override fun onItemClicked(item: CommonModel) {
               onMenuItemClicked(item)
            }
            override fun onItemLongClicked(item: CommonModel): Boolean {
                Log.d("zoyeb", "Long clicked" + item.toString())
                return true
            }

        })
        return frag
    }

    private fun onMenuItemClicked(item: CommonModel) {
        when (item.category) {
            DashboardMenusCat.LOGOUT.ordinal -> {
                Log.d("zoyeb", "onMenuItemClicked: Logout")
            }
            else -> {
                dashboardViewModel.dashboardMenuClicked(item)
                replaceFragment(
                    item, getDashboardMenuListingFraggment(),
                    BackstackFragmentName.DashboardMenuFragment,
                    true
                )
            }
        }
    }

    private fun getDashboardMenuListingFraggment(): Fragment {
        var frag = DashboardMenuListingFragment(object : DashboardMenuListingFragmenrListnere {
            override fun onItemClicked(item: CommonModel) {
                Log.d("zoyeb", "onItemClicked: " + item.toString())
                gotoDashDetailsActivity(item)
            }


        })
        return frag
    }

    private fun gotoDashDetailsActivity(item: CommonModel) {
        Intent(this@DashboardActivity, DetailsActivity::class.java).apply {
                putExtra("Details_Data", item)
                startActivity(this)
        }
    }

    class FragmentListner():DashboardMenuListingFragmenrListnere{
        override fun onItemClicked(item: CommonModel) {

        }

    }


    private fun replaceFragment(
        menu: CommonModel,
        fragment: Fragment,
        tag: String,
        isBackstack: Boolean
    ) {
        setToolbar(true,menu.name)
        var transaction = supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fragment_slide_left,
                R.anim.fragment_slide_right,
                R.anim.fragment_slide_left,
                R.anim.fragment_slide_right
            )
            .replace(binding.dashboardContainer.id, fragment)
        transaction = if (isBackstack) transaction.addToBackStack(tag) else transaction
        transaction.commit()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.dashboardContainer.id, fragment)
            .setCustomAnimations(
                R.anim.fragment_slide_left,
                R.anim.fragment_slide_right,
                R.anim.fragment_slide_left,
                R.anim.fragment_slide_right
            )
            .addToBackStack(BackstackFragmentName.DASHBOARDFRAGMENT)
            .commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(binding.dashboardContainer.id)
        if (fragment is DashboardMenuListingFragment) {
            supportFragmentManager.popBackStack()
            setToolbar(false, "Dashboard")
        } else if (fragment is DashboardMenuFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    fun setToolbar(arrawVisibility: Boolean, title: String) {
        toolbarBinding.toolbarTitle = title
        toolbarBinding.isBackButtonVisible = arrawVisibility
        toolbarBinding.notifyChange()
    }

    override fun onArrowButtonClicked() {
        onBackPressed()
    }
}