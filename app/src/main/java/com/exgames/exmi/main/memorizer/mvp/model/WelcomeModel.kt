package com.exgames.exmi.main.memorizer.mvp.model

import com.exgames.exmi.main.memorizer.SharedPreferenceRepository

class WelcomeModel {

    var sharedPreferenceRepository: SharedPreferenceRepository? = null

    constructor(sharedPreferenceRepository: SharedPreferenceRepository) {
        this.sharedPreferenceRepository = sharedPreferenceRepository

    }

    fun getShardPreferenceSoundActivated(): Boolean {
        return sharedPreferenceRepository!!.getSound()
    }

    fun putSharedPreferenceSoundActivated(soundActivated: Boolean) {
        sharedPreferenceRepository!!.putSound(soundActivated)

    }

}