package com.example.galgotialfest.ui.listners

import com.example.galgotialfest.model.GameModel

interface firebaseLoadDataListener {
    fun ondataLoaded(game: GameModel)
}