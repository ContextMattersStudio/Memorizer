package com.exgames.exmi.main.memorizer.mvp.view

import android.media.MediaPlayer
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.base.SettingsActivity


class SettingsView : BaseView {
    private var mediaPlayer: MediaPlayer?

    constructor(settingsActivity: SettingsActivity) {
        this.activity = settingsActivity
        mediaPlayer = MediaPlayer.create(this.activity!!.applicationContext, R.raw.piglevelwin2)

    }

    fun playMusic() {
        if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {
            mediaPlayer = MediaPlayer.create(activity?.baseContext, R.raw.splashscreenloop)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    fun stopPlayingMusic() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.pause()
            mediaPlayer!!.seekTo(0)
        }
    }

}