package com.exgames.exmi.main.memorizer.app

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import com.exgames.exmi.main.bus.RxBusKotlin
import com.exgames.exmi.main.bus.events.base.ShouldPlayMusicBusObserver
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.background.BackgroundMusicManager
import com.exgames.exmi.main.memorizer.persistent.preferences.SharedPreferenceRepository


/**
 * Tracks the Lifecycle of the whole application thanks to {@link LifecycleObserver}.
 * This is registered via {@link ProcessLifecycleOwner#get()} ()}. The events are designed
 * to be dispatched with delay (by design) so activity rotations don't trigger these calls.
 * See: https://developer.android.com/reference/android/arch/lifecycle/ProcessLifecycleOwner.html
 */
class LifecycleListener(context: Context, sharedPreferenceRepository: SharedPreferenceRepository) : LifecycleObserver {

    private lateinit var musicManager: BackgroundMusicManager
    private lateinit var sharedPreferenceRepository: SharedPreferenceRepository

    init {
        initializeMediaPlayer(context, sharedPreferenceRepository)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        RxBusKotlin.subscribe(this, object : ShouldPlayMusicBusObserver() {
            override fun onEvent(value: ShouldPlayMusicEvent) {
                if (value.shouldPlayMusic) {
                    musicManager.resumeMusic()
                } else {
                    musicManager.pauseMusic()
                }
            }
        })
        if (sharedPreferenceRepository.getMusic()) {
            musicManager.resumeMusic()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onMoveToBackground() {
        if (sharedPreferenceRepository.getMusic()) {
            musicManager.pauseMusic()
        }
        RxBusKotlin.clear(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyed() {
        musicManager.releaseMusicPlayer()
    }

    private fun initializeMediaPlayer(context: Context, sharedPreferenceRepository: SharedPreferenceRepository) {
        this.sharedPreferenceRepository = sharedPreferenceRepository
        musicManager = BackgroundMusicManager(context, R.raw.splashscreenloop, sharedPreferenceRepository)
    }

}