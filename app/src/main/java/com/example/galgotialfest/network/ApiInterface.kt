package com.example.galgotialfest.network

import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.util.Dataprovider

class ApiInterface {
    suspend fun getMenus() = Dataprovider.getMenusfromDB()
    suspend fun getGameData(menu:CommonModel
    )=Dataprovider.getMenuDataByCategory(menu)
}