package com.exgames.exmi.main.bus

import com.exgames.exmi.main.bus.events.base.BusObserverKotlin
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.HashMap

object RxBusKotlin {
    private val disposableMap = HashMap<Any, CompositeDisposable>()
    private val publishSubject = PublishSubject.create<Any>()

    fun post(`object`: Any) {
        publishSubject.onNext(`object`)
    }

    fun subscribe(key: Any,  busObserverKotlin: BusObserverKotlin<*>) {
        var compositeDisposable: CompositeDisposable? = disposableMap[key]
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(publishSubject.subscribe(busObserverKotlin))
        disposableMap[key] = compositeDisposable
    }

    fun clear(key: Any) {
        val compositeDisposable = disposableMap[key]
        compositeDisposable?.clear()
        disposableMap.remove(key)
    }

    fun clearAll() {
        for ((_, value) in disposableMap) {
            value.clear()
        }
        disposableMap.clear()
    }
}
