package com.example.galgotialfest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.model.DashboardMenus
import com.example.galgotialfest.repos.SplashReposatory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class DashboardViewModel constructor(
):ViewModel(

) {
    var dashboardMenus: MutableLiveData<List<CommonModel>> = MutableLiveData()
    var menusClickData: MutableLiveData<List<CommonModel>> = MutableLiveData()
    fun loadMenus() {
        viewModelScope.launch {
            dashboardMenus.value = SplashReposatory().getDashBoardMenus()
        }
    }

    fun dashboardMenuClicked(menuItem: CommonModel) {
        viewModelScope.launch {
            menusClickData.value = SplashReposatory().getMenuClickedData(menuItem)
            Log.d("zoyeb", "dashboardMenuClicked data came: ")
        }
    }
}