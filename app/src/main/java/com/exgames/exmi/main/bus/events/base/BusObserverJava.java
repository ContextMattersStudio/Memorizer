package com.exgames.exmi.main.bus.events.base;


import io.reactivex.functions.Consumer;

public abstract class BusObserverJava<T> implements Consumer<Object> {
    public final Class<T> clazz;

    public BusObserverJava(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void accept(Object value) throws Exception {
        if (value.getClass() == clazz) {
            onEvent((T) value);
        }
    }

    public abstract void onEvent(T value);
}
