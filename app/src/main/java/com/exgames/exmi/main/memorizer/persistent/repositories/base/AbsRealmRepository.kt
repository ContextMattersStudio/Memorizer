package com.exgames.exmi.main.memorizer.persistent.repositories.base

import android.util.Log
import com.exgames.exmi.main.memorizer.persistent.data_source.RealmDataSource
import com.exgames.exmi.main.memorizer.persistent.mappers.RealmMapper
import io.realm.Realm
import io.realm.RealmObject


open class AbsRealmRepository<S, I, O : RealmObject>(protected var services: S?,
                                                     protected var dataSource: RealmDataSource<O>,
                                                     protected var mapper: RealmMapper<I, O>) {
    fun cancelTransactions() {
        try {
            val realm: Realm = Realm.getDefaultInstance()
            if (realm.isInTransaction) {
                realm.cancelTransaction()
            }
        } catch (e: Exception) {
            Log.e(e.toString(), e.message)
        }
    }
}