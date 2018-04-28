package com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces

import android.support.annotation.NonNull
import com.exgames.exmi.main.memorizer.persistent.domain.HighScores
import com.exgames.exmi.main.memorizer.persistent.entities.HighScore
import com.exgames.exmi.main.memorizer.persistent.repositories.base.RealmRepository

interface HighScoresRepository : RealmRepository {
    fun saveHighScore(@NonNull highScores: HighScores)
    fun getAllHighScores(): MutableList<HighScores>
    fun getFirst(): HighScores
    fun clearAll()
    fun getTopXElements(elementsToGet: Int): MutableList<HighScores>
}