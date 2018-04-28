package com.exgames.exmi.main.memorizer.persistent.entities

import io.realm.annotations.PrimaryKey


data class HighScore(var userNameHighScore: String
                     , var score: Int) {
    @PrimaryKey
    var primaryKey: Int = 0
}