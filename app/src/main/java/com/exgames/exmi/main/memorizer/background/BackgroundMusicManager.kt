package com.exgames.exmi.main.memorizer.background

import android.content.Context
import android.media.MediaPlayer
import android.os.AsyncTask
import com.exgames.exmi.main.memorizer.persistent.preferences.SharedPreferenceRepository


class BackgroundMusicManager(context: Context, backgroundMusic: Int, private val sharedPreferenceRepository: SharedPreferenceRepository) : AsyncTask<Void, Void, Void>() {
    private var player: MediaPlayer = MediaPlayer.create(context, backgroundMusic)

    override fun doInBackground(vararg params: Void): Void? {
        player.isLooping = true // Set looping
        player.setVolume(100f, 100f)
        //player.start()
        return null
    }

    fun resumeMusic() {
        player.isLooping = true // Set looping
        player.seekTo(0)
        player.start()
    }

    fun pauseMusic() {
        player.pause()
    }

    fun releaseMusicPlayer() {
        player.release()
    }

}