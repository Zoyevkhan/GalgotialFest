package com.example.galgotialfest.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galgotialfest.database.FirebaseHandler
import com.example.galgotialfest.enums.GameType
import com.example.galgotialfest.enums.SubCategory
import com.example.galgotialfest.enums.UserType
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.model.DetailsUser
import com.example.galgotialfest.model.GameModel
import com.example.galgotialfest.repos.SplashReposatory
import com.example.galgotialfest.ui.listners.FirebaseAddLitener
import com.example.galgotialfest.util.LoadingState
import com.example.galgotialfest.util.ViewState
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailsViewModel  constructor(
)  :ViewModel(){
     var detailsUser:DetailsUser= DetailsUser()
     var game:MutableLiveData<GameModel> = MutableLiveData()
     var addFragmentMessage:MutableLiveData<LoadingState> = MutableLiveData()


    fun addUserClick(userType: Int, gameMetaData: CommonModel) {
        addFragmentMessage.value = LoadingState.isLoading
        detailsUser.userType = getUsertypeByPosition(userType)
        viewModelScope.launch {
            SplashReposatory().addUserToDatabase(
                detailsUser,
                gameMetaData,
                object : FirebaseAddLitener {
                    override fun onSuccessfullyAdded() {
                        addFragmentMessage.value =
                            LoadingState.Sucess(detailsUser.userName + ": is Added Succesfully")
                        saveDataLocally(detailsUser, gameMetaData)
                    }
                    override fun onErrorMessage(message: String) {
                        addFragmentMessage.value = LoadingState.Fail(message)
                    }
                })
        }
    }


    fun loadData(parentCat:GameType,subtype:SubCategory) {
        FirebaseHandler().getDataFromFirebaseByGameName(parentCat, subtype) { data ->
            when (data) {
                is ViewState.Success -> {
                    Log.d("", "loadData: "+data.result.toString())
                    game.value = data.result
                }
            }
        }

    }








    private fun saveDataLocally(
        user: DetailsUser,
        gamemetadata: CommonModel
    ) {
        var newUser = DetailsUser(user.userName, user.userId, user.userType)
        var model = game.value ?: GameModel()
        if (user.userType == UserType.PARTICIPANT) {
            model = game.value ?: GameModel()
            var participants = model?.participants ?: mutableListOf()
            participants.add(newUser)
            model.participants = participants
        } else if (user.userType == UserType.TEACHERCOORDINATOR) {
            model = game.value ?: GameModel()
            var teacherCoordinators = model?.teachersCoordinators ?: mutableListOf()
            teacherCoordinators.add(newUser)
            model.teachersCoordinators = teacherCoordinators
        } else {
            model = game.value ?: GameModel()
            var studentCoordinators = model?.studentCoordinators ?: mutableListOf()
            studentCoordinators.add(newUser)
            model.studentCoordinators = studentCoordinators
        }
        model?.parentType = gamemetadata.parentCat
        model?.subCat = gamemetadata.subCategory
        game.value = model
    }


    fun getUsertypeByPosition(position:Int):UserType{
        if(position==0)
            return UserType.TEACHERCOORDINATOR
        else if(position==1)
            return UserType.STUDENTCOORDINATOR
        else return UserType.PARTICIPANT
    }

    fun refershUserdata() {
        detailsUser.userId=""
        detailsUser.userName=""
    }
}