package com.exgames.exmi.main.memorizer.persistent.data_source

import com.exgames.exmi.main.memorizer.persistent.realm_entities.RHighScores


class RHighscoresDataSource : RealmDataSource<RHighScores>(RHighScores::class.java) {
    override fun getPrimaryKey(): String = RHighScores.PRIMARY_KEY
}