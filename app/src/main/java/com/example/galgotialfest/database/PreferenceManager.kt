package com.example.galgotialfest.database

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
class PreferenceManager(@ApplicationContext context: Context) {
    init {
        Log.d("khan", ":creating the Manager")
    }
   private lateinit var preferenceManager: SharedPreferences
    init {
        preferenceManager= context.getSharedPreferences("zoyeb", Context.MODE_PRIVATE)
    }
    lateinit var editor: SharedPreferences.Editor
    fun getString(key:String):String ?= preferenceManager.getString(key,"")
    fun saveString(key:String,value:String){
        preferenceManager.edit()
            .putString(key,value)
            .apply()
    }
}