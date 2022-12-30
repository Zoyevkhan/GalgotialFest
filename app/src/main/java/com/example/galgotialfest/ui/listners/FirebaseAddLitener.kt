package com.example.galgotialfest.ui.listners

import android.os.Message

interface FirebaseAddLitener {
    fun onSuccessfullyAdded()
    fun onErrorMessage(message: String)
}