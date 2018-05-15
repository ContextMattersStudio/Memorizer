package com.exgames.exmi.main.memorizer

import android.app.Activity
import android.content.SharedPreferences

class SharedPreferenceRepository(activity: Activity) {
    val PREFS_FILENAME = "com.exgames.prefs"
    var preferences: SharedPreferences? = null
    private val SOUND_ACTIVATED = "SOUND_ACTIVATED"
    private val MUSIC_ACTIVATED = "MUSIC_ACTIVATED"

    init {
        preferences = activity.getSharedPreferences(PREFS_FILENAME, 0)
    }

    fun putMusic(soundActivated: Boolean) {
        val editor = preferences?.edit()
        editor?.putBoolean(MUSIC_ACTIVATED, soundActivated)
        editor?.apply()
    }

    fun getMusic(): Boolean {
        return preferences!!.getBoolean(MUSIC_ACTIVATED, true)
    }

    fun getSounds(): Boolean {
        return preferences!!.getBoolean(SOUND_ACTIVATED, true)
    }

    fun putSounds(soundActivated: Boolean) {
        val editor = preferences?.edit()
        editor?.putBoolean(SOUND_ACTIVATED, soundActivated)
        editor?.apply()
    }
}
