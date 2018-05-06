package com.exgames.exmi.main.memorizer

import android.app.Activity
import android.content.SharedPreferences

class SharedPreferenceRepository(activity: Activity) {
    val PREFS_FILENAME = "com.exgames.prefs"
    var preferences: SharedPreferences? = null
    private val SOUND_ACTIVATED = "SOUND_ACTIVATED"

    fun putSound(soundActivated: Boolean) {
        val editor = preferences?.edit()
        editor?.putBoolean(SOUND_ACTIVATED, soundActivated)
        editor?.apply()
    }

    fun getSound(): Boolean {
        return preferences!!.getBoolean(SOUND_ACTIVATED, true)
    }

    init {
        preferences = activity.getSharedPreferences(PREFS_FILENAME, 0)
    }
}
