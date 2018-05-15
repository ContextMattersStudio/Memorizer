package com.exgames.exmi.main.memorizer.app

import android.app.Application
import android.arch.lifecycle.ProcessLifecycleOwner
import com.exgames.exmi.main.memorizer.persistent.preferences.SharedPreferenceRepository


class MemorizerApp : Application() {

    private val lifecycleListener: LifecycleListener by lazy {
        val sharedPreferenceRepository = SharedPreferenceRepository(applicationContext)
        LifecycleListener(applicationContext, sharedPreferenceRepository)
    }

    override fun onCreate() {
        super.onCreate()
        setupLifecycleListener()
    }

    private fun setupLifecycleListener() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleListener)
    }
}