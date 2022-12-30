package com.example.galgotialfest.util
sealed class LoadingState{
    object isLoading : LoadingState()
    data class Sucess(val message: String) : LoadingState()
    data class Fail(val message : String) : LoadingState()
}

