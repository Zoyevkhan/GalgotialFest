package com.example.galgotialfest.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
/*import com.example.galgotialfest.util.DownloadWorker*/

class SplashViewModel(val context: Context):ViewModel(){
    override fun onCleared() {
        super.onCleared()

    }

    fun downloadDataFromFirebase(){

        //start worker
        /*val oneTimeWorkRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
        WorkManager.getInstance(context).enqueue(oneTimeWorkRequest)*/


    }
}