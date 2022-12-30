package com.example.galgotialfest.ui.listners

import com.example.galgotialfest.enums.UserType
import com.example.galgotialfest.model.DetailsUser

interface FragmentDetailsListingListener {
    fun onItemAddClicked(userType: Int)
    fun onUserMakeWinnerClicked(user:DetailsUser):Boolean
}