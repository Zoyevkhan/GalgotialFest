package com.example.galgotialfest.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.galgotialfest.BR
import com.example.galgotialfest.R
import com.example.galgotialfest.database.FirebaseHandler
import com.example.galgotialfest.databinding.ActivitySplashBinding
import com.example.galgotialfest.model.City
import com.example.galgotialfest.model.DetailsUser
import com.example.galgotialfest.model.GameModel
import com.example.galgotialfest.model.User
import com.example.galgotialfest.repos.SplashReposatory
import com.example.galgotialfest.ui.fragments.CustomDialogFragment.Companion.TAG
import com.example.galgotialfest.ui.listners.firebaseLoadDataListener
import com.example.galgotialfest.util.ViewState
/*import com.example.galgotialfest.util.DownloadWorker*/
import com.example.galgotialfest.viewmodels.SplashViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.callbackFlow

class SplashActivity : AppCompatActivity() {
    lateinit var user: User
    val splashViewModel: SplashViewModel by viewModels<SplashViewModel>()
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        setContentView(binding.root)
        user = User("zoyeb", 22, City("Deljgugughi"))
        /*binding.root.setOnClickListener {
            FirebaseHandler().getDataFromFirebaseByGameName("indoor", "bgmi") {
                when (it) {
                    is ViewState.Success -> {
                        Log.d("Farazkhan", it.result.toString())
                    }
                }
            }
        }*/
        /* binding.image=R.drawable.safagif*//*

        GlobalScope.launch {
                val job = launch {
                    try {
                        throw Exception()
                    }catch (e:Exception){
                        Log.d("zoyeb","Handled")
                    }
                }
                delay(3000)
                Log.d("zoyeb", "under onCreate: ")

        }
        Log.d("zoyeb", "onCreate: ")*/
        /*initDownloadWorker()*/

}

    suspend fun getUserData(): List<City> {
        delay(3000)
        var list= listOf<City>(
            City("hhhh"),
            City("hhhh"), City("hhhh")
        )
        return list
    }
    /* private fun initDownloadWorker() {
      val oneTimeWorkRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
      WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
      WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
          .observe(this, Observer { workInfo ->
              if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                  Log.d("zoyeb", "onCreate: "+"finished"+workInfo.state)
                  Intent(this@SplashActivity,UserSelectionActivity::class.java).apply {
                      startActivity(this)
                  }
                  finish()
              }else{
                  Log.d("zoyeb", "onCreate: "+"failed"+workInfo.state)
              }
          })
  }*/

}