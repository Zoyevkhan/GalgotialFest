package com.example.galgotialfest.binding.bindingLayoutModel

import androidx.databinding.BaseObservable
import com.example.galgotialfest.ui.listners.BindingToolbarClickListner

data class ToolbarBinding(
   public var toolbarTitle:String,
   public var isBackButtonVisible:Boolean,
   public var clickListner: BindingToolbarClickListner
):BaseObservable()