package com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces.implementations

import android.util.Log
import com.exgames.exmi.main.memorizer.persistent.data_source.RHighscoresDataSource
import com.exgames.exmi.main.memorizer.persistent.data_source.RealmDataSource
import com.exgames.exmi.main.memorizer.persistent.domain.HighScores
import com.exgames.exmi.main.memorizer.persistent.entities.HighScore
import com.exgames.exmi.main.memorizer.persistent.mappers.RealmMapper
import com.exgames.exmi.main.memorizer.persistent.realm_entities.RHighScores
import com.exgames.exmi.main.memorizer.persistent.repositories.base.AbsRealmRepository
import com.exgames.exmi.main.memorizer.persistent.repositories.base.RealmRepository
import com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces.HighScoresRepository
import io.realm.Realm


class HighScoresRepositoryImpl(any: Any?, dataSource: RealmDataSource<RHighScores>, realmMapper: RealmMapper<HighScores, RHighScores>)
    : AbsRealmRepository<Any?, HighScores, RHighScores>(any, dataSource, realmMapper), HighScoresRepository {
    override fun cancelTransaction() {
        super.cancelTransactions()
    }

    override fun saveHighScore(highScores: HighScores) {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            dataSource as RHighscoresDataSource
            dataSource.create(realm, mapper.transform(highScores))
        } catch (e: Exception) {
            Log.e(e.toString(), e.message)
        }
    }

    override fun getAllHighScores(): MutableList<HighScores> {
        var result = ArrayList<HighScores>()
        try {
            val realm: Realm = Realm.getDefaultInstance()
            dataSource as RHighscoresDataSource
            var entities: List<RHighScores> = dataSource.getAll(realm)
            for (entity: RHighScores in entities) {
                result.add(mapper.transform(entity))
            }
        } catch (e: Exception) {
            Log.e(e.toString(), e.message)
        }
        return result
    }

    override fun getFirst(): HighScores {
        var result = RHighScores()
        try {
            val realm: Realm = Realm.getDefaultInstance()
            dataSource as RHighscoresDataSource
            if (dataSource.getFirst(realm, RHighScores()) != null) {
                result = dataSource.getFirst(realm, RHighScores()) as RHighScores
            }
        } catch (e: Exception) {
            Log.e(e.toString(), e.message)
        }
        return mapper.transform(result)
    }

    override fun clearAll() {
        try {
            val realm = Realm.getDefaultInstance()
            dataSource as RHighscoresDataSource
            dataSource.clearAll(realm)
        } catch (e: Exception) {
            Log.e(e.toString(), e.message)
        }
    }

    override fun getTopXElements(elementsToGet: Int): MutableList<HighScores> {
        var result = ArrayList<HighScores>()
        try {
            val realm: Realm = Realm.getDefaultInstance()
            dataSource as RHighscoresDataSource
            var entities: List<RHighScores> = dataSource.getTopXElements(realm, RHighScores.FIELD_TO_SORT_BY, elementsToGet)
            for (entity: RHighScores in entities) {
                result.add(mapper.transform(entity))
            }
        } catch (e: Exception) {
            Log.e(e.toString(), e.message)
        }
        return result
    }

    override fun clearLast() {
        try {
            val realm = Realm.getDefaultInstance()
            dataSource as RHighscoresDataSource
            val realmLastElement = dataSource.getTopXElements(realm, RHighScores.FIELD_TO_SORT_BY, getCount().toInt()).last()
            val lastElement = mapper.transform(realmLastElement)
            dataSource.clear(realm, RHighScores.FIELD_TO_SORT_BY, RHighScores.FIELD_USER_NAME, lastElement.score, lastElement.userName)

        } catch (e: Exception) {
            Log.e(e.toString(), e.message)
        }
    }

    override fun getCount(): Long {
        var count = 0L
        try {
            val realm = Realm.getDefaultInstance()
            dataSource as RHighscoresDataSource
            count = dataSource.count(realm)
        } catch (e: Exception) {
            Log.e(e.toString(), e.message)
        }
        return count
    }
}