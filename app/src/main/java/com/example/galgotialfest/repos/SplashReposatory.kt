package com.example.galgotialfest.repos

import android.util.Log
import com.example.galgotialfest.database.FirebaseHandler
import com.example.galgotialfest.database.PreferenceManager
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.model.DetailsUser
import com.example.galgotialfest.model.User
import com.example.galgotialfest.network.ApiInterface
import com.example.galgotialfest.ui.listners.FirebaseAddLitener
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject


class SplashReposatory (
   /* val preferenceManager: PreferenceManager*/
) {

   suspend fun getDashBoardMenus():List<CommonModel>{
     //  Log.d("zoyeb", preferenceManager.toString() + " "+ preferenceManager.getString("data"))
       //dagger hilt
     /*  delay(4000)*/

        return ApiInterface().getMenus()
    }
    fun check1(a:Int,b:Int,result:(String)->Unit,rs:(String)->Unit){
        Log.d("zoyeb", "check1:")
        if(a>b)
            result.invoke("done")
        else
            rs.invoke("Not done")
    }

    suspend fun getMenuClickedData(menuItem: CommonModel) :List<CommonModel>{
        return ApiInterface().getGameData(menuItem)

    }

    suspend fun addUserToDatabase(
        user: DetailsUser,
        gamemetadata: CommonModel,
        callback: FirebaseAddLitener
    ){
            FirebaseHandler().saveGameToFirebase(user,gamemetadata,callback)
    }

}