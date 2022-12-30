package com.example.galgotialfest.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class Darawables(
    @get:Bindable
    var list:MutableList<UserTypeDrawable>

):BaseObservable()
