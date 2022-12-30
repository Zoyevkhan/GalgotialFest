package com.example.galgotialfest.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class DashboardMenus:BaseObservable() {
    @get:Bindable
     var list:List<CommonModel> = mutableListOf()
    set(value) {
        field=value
        notifyChange()
    }

}