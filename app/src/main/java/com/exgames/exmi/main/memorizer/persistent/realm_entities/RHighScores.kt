package com.exgames.exmi.main.memorizer.persistent.realm_entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class RHighScores : RealmObject() {

    companion object {
        val PRIMARY_KEY = "ID"
        val FIELD_TO_SORT_BY = "score"
        val FIELD_USER_NAME = "userName"
    }

    lateinit var userName: String
    lateinit var score: String
}