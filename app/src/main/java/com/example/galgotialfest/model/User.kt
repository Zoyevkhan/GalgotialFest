package com.example.galgotialfest.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import java.io.ObjectInput
import java.io.Serializable

data class User(var name:String,var age:Int,var city: City):BaseObservable()