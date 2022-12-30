package com.example.galgotialfest.util

import com.example.galgotialfest.model.GameModel

sealed class ViewState {
    data class Success(val result: GameModel) : ViewState()
    data class Error(val errorMessage: String) : ViewState()
    object Loading : ViewState()

}