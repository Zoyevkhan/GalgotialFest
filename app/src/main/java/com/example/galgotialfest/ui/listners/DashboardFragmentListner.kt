package com.example.galgotialfest.ui.listners

import com.example.galgotialfest.model.CommonModel

interface DashboardFragmentListner{
    fun onItemClicked(item:CommonModel)
    fun onItemLongClicked(item:CommonModel):Boolean

}