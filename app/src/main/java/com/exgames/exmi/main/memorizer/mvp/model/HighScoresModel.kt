package com.exgames.exmi.main.memorizer.mvp.model

import com.exgames.exmi.main.memorizer.persistent.domain.HighScores
import com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces.HighScoresRepository


class HighScoresModel(repository: HighScoresRepository) {
    private var repository: HighScoresRepository? = repository

    fun getAllHighScores(): MutableList<HighScores> {
        return repository!!.getAllHighScores()
    }

    fun getFirstHighScore(): HighScores? {
        return repository?.getFirst()
    }

    fun getTopXElements(elementsToGet: Int): MutableList<HighScores> {
        return repository!!.getTopXElements(elementsToGet)
    }

    fun clearRecords() {
        repository?.clearAll()
    }
}