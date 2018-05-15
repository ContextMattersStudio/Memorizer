package com.exgames.exmi.main.memorizer.mvp.model

import com.exgames.exmi.main.memorizer.persistent.preferences.SharedPreferenceRepository

class WelcomeModel {

    var sharedPreferenceRepository: SharedPreferenceRepository? = null

    constructor(sharedPreferenceRepository: SharedPreferenceRepository) {
        this.sharedPreferenceRepository = sharedPreferenceRepository

    }

    fun getSharedPreferenceIsMusicActivated(): Boolean {
        return sharedPreferenceRepository!!.getMusic()
    }

}