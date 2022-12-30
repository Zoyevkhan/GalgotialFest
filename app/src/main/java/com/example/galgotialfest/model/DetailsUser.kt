package com.example.galgotialfest.model

import com.example.galgotialfest.enums.UserType

data class DetailsUser(
    var userName:String=" ",
    var userId:String=" ",
    var userType: UserType=UserType.PARTICIPANT
)
