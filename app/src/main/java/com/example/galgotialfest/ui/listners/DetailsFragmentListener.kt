package com.example.galgotialfest.ui.listners

import com.example.galgotialfest.enums.DashboardMenusCat
import com.example.galgotialfest.enums.DetailsFragmentCLickedMenu

interface DetailsFragmentListener{
    fun onItemClicked(itemType:DetailsFragmentCLickedMenu)
}