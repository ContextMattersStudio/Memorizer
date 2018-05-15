package com.exgames.exmi.main.memorizer.mvp.model

import com.exgames.exmi.main.memorizer.persistent.preferences.SharedPreferenceRepository


class SettingsModel {

    var sharedPreferenceRepository: SharedPreferenceRepository? = null

    constructor(sharedPreferenceRepository: SharedPreferenceRepository) {
        this.sharedPreferenceRepository = sharedPreferenceRepository

    }

    fun getSharedPreferenceMusicActivated(): Boolean {
        return sharedPreferenceRepository!!.getMusic()
    }

    fun putSharedPreferenceMusicActivated(checked: Boolean) {
        sharedPreferenceRepository!!.putMusic(checked)
    }

    fun putSharedPreferenceSoundsActivated(checked: Boolean) {
        sharedPreferenceRepository!!.putSounds(checked)
    }

    fun getSharedPreferenceSoundsActivated(): Boolean {
        return sharedPreferenceRepository!!.getSounds()
    }
}