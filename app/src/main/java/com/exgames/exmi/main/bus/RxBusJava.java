package com.exgames.exmi.main.bus;


import android.support.annotation.NonNull;

import com.exgames.exmi.main.bus.events.base.BusObserverJava;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

public final class RxBusJava {
    private static Map<Object, CompositeDisposable> disposableMap = new HashMap<>();
    private static PublishSubject<Object> publishSubject = PublishSubject.create();

    private RxBusJava() {
    }

    public static void post(@NonNull Object object) {
        publishSubject.onNext(object);
    }

    public static void suscribe(@NonNull Object key, @NonNull BusObserverJava busObserverJava) {
        CompositeDisposable compositeDisposable = disposableMap.get(key);
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(publishSubject.subscribe(busObserverJava));
        disposableMap.put(key, compositeDisposable);
    }

    public static void clear(@NonNull Object key) {
        CompositeDisposable compositeDisposable = disposableMap.get(key);
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        disposableMap.remove(key);
    }

    public static void clearAll() {
        for (Map.Entry<Object, CompositeDisposable> entry : disposableMap.entrySet()) {
            entry.getValue().clear();
        }
        disposableMap.clear();
    }
}
