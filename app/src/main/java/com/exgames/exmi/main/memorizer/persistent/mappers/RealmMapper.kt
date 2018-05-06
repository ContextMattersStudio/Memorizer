package com.exgames.exmi.main.memorizer.persistent.mappers

import android.support.annotation.NonNull
import io.realm.RealmObject


interface RealmMapper<I, O : RealmObject> {

    @NonNull
    fun transform(@NonNull input: I): O

    @NonNull
    fun transform(@NonNull input: O): I

}
