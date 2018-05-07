package com.exgames.exmi.main.memorizer.mvp.model

import com.exgames.exmi.main.memorizer.SharedPreferenceRepository


class SettingsModel {

    var sharedPreferenceRepository: SharedPreferenceRepository? = null

    constructor(sharedPreferenceRepository: SharedPreferenceRepository) {
        this.sharedPreferenceRepository = sharedPreferenceRepository

    }

    fun getSharedPreferenceMusicActivated(): Boolean {
        return sharedPreferenceRepository!!.getSound()
    }

    fun putSharedPreferenceMusicActivated(checked: Boolean) {
        sharedPreferenceRepository!!.putSound(checked)
    }

    fun getSharedPreferenceSoundActivated(): Boolean {
        return sharedPreferenceRepository!!.getSound()
    }

    fun putSharedPreferenceSoundActivated(checked: Boolean) {
        sharedPreferenceRepository!!.putSound(checked)
    }
}