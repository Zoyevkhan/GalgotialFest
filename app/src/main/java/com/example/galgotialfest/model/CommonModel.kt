package com.example.galgotialfest.model

import androidx.databinding.BaseObservable
import com.example.galgotialfest.enums.GameType
import com.example.galgotialfest.enums.SubCategory
import java.io.Serializable

data class CommonModel(
    val name:String,
    val icon:Int,
    val category: Int,
    val parentCat:GameType?=null,
    val subCategory: SubCategory?=null
):BaseObservable(),Serializable
