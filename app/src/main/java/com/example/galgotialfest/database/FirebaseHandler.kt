package com.example.galgotialfest.database

import android.util.Log
import com.example.galgotialfest.enums.GameType
import com.example.galgotialfest.enums.SubCategory
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.model.DetailsUser
import com.example.galgotialfest.model.GameModel
import com.example.galgotialfest.ui.activity.DetailsActivity
import com.example.galgotialfest.ui.listners.FirebaseAddLitener
import com.example.galgotialfest.ui.listners.firebaseLoadDataListener
import com.example.galgotialfest.util.Dataprovider
import com.example.galgotialfest.util.ViewState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.coroutines.*
import javax.security.auth.callback.Callback
import kotlin.reflect.KClass

class FirebaseHandler {
    fun saveGameToFirebase(user: DetailsUser, gamemetadata: CommonModel, callback:FirebaseAddLitener){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("galgoatiafest")
        myRef.child(Dataprovider.parantCategoryMap.get(gamemetadata.parentCat)!!)
            .child(Dataprovider.subCategoryMap.get(gamemetadata.subCategory)!!)
            .child(getUserType(user))
            .child(user.userId)
            .setValue(user).addOnSuccessListener {
                callback.onSuccessfullyAdded()
                Log.d("database", "onCreate: ")
            }.addOnFailureListener {
                callback.onErrorMessage(it.localizedMessage)
                Log.d("database", "onCreate: " + it.localizedMessage)
            }
            .addOnCanceledListener {
                callback.onErrorMessage("something went wrong")
            }


    }




    private fun getUserType(gamemetadata: DetailsUser): String {
        if(gamemetadata.userType?.ordinal==0){
            return "TeachersCoordinators"
        }else if(gamemetadata.userType?.ordinal==1){
            return "StudentCoordinators"
        }else{
            return "Participants"
        }
    }

    private fun getChildByParentCat(gamemetadata: CommonModel): String {
        if(gamemetadata.parentCat?.ordinal==0){
            return "Outdoor"
        }else if(gamemetadata.parentCat?.ordinal==1){
            return "Indoor"
        }else{
           return "Fungames"
        }
    }
    private fun getChildBySubCat(gamemetadata: CommonModel): String {
        if(gamemetadata.subCategory?.ordinal==0){
            return "NukkadNatak"
        }else if(gamemetadata.subCategory?.ordinal==1){
            return "Indoor"
        }else{
            return "Fungames"
        }
    }
     fun getDataFromFirebaseByGameName(
        parentName:GameType,
        childName:SubCategory,
        callBack: (ViewState)->Unit
    ){
        val database = FirebaseDatabase.getInstance()
        val participantList= mutableListOf<DetailsUser>()
        val teachersCoordinatorList= mutableListOf<DetailsUser>()
        val studentCoordinatorList= mutableListOf<DetailsUser>()
        val myRef = database.getReference("galgoatiafest")
        myRef.child(Dataprovider.parantCategoryMap.get(parentName)!!).child(Dataprovider.subCategoryMap.get(childName)!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    if(it.key.equals("TeachersCoordinators")){
                        it.children.forEach{
                            var user:DetailsUser= Gson().fromJson(it.value.toString(),DetailsUser::class.java)
                            teachersCoordinatorList.add(user)
                        }
                    }

                    if(it.key.equals("StudentCoordinators")){
                        it.children.forEach{
                            var user:DetailsUser= Gson().fromJson(it.value.toString(),DetailsUser::class.java)
                            studentCoordinatorList.add(user)
                        }
                    }


                    if(it.key.equals("Participants")){
                        it.children.forEach{
                            var user:DetailsUser= Gson().fromJson(it.value.toString(),DetailsUser::class.java)
                            participantList.add(user)
                        }
                    }
                }
                var game:GameModel= GameModel()
                game.parentType=parentName
                game.subCat=childName
                game.participants=participantList
                game.studentCoordinators=studentCoordinatorList
                game.teachersCoordinators=teachersCoordinatorList
                callBack.invoke(ViewState.Success(game))
                Log.d("datazouyebhan", "onDataChange: "+dataSnapshot.value.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("zoyeb", "onCancelled: "+error.message)

            }
        })

    }

}

private fun String.toSubPojo(): SubCategory? {
   return SubCategory.SINGING
}

private fun String.toParentPojo(): GameType? {
    if(this.equals("Outdoor",true)){
        return GameType.OUTDOOR
    }else if(this.equals("Indoor",true)){
        return GameType.FUNGAME
    }else{
        return GameType.FUNGAME
    }
}

