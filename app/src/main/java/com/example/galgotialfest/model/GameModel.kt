package com.example.galgotialfest.model

import com.example.galgotialfest.enums.GameType
import com.example.galgotialfest.enums.SubCategory

data class GameModel(
    var parentType:GameType?=null,
    var subCat:SubCategory?=null,
    var participants:MutableList<DetailsUser>?=null,
    var teachersCoordinators:MutableList<DetailsUser>?=null,
    var studentCoordinators:MutableList<DetailsUser>?=null,
    var winnerList:MutableList<DetailsUser>?=null
)
