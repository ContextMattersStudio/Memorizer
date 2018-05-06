package com.exgames.exmi.main.memorizer.mvp.presenter

import com.exgames.exmi.main.memorizer.mvp.model.SettingsModel
import com.exgames.exmi.main.memorizer.mvp.view.SettingsView


class SettingsPresenter : BasePresenter {
    lateinit var view: SettingsView
    lateinit var model: SettingsModel

    constructor(view: SettingsView, model: SettingsModel) {
        baseView = view
        this.view = view
        this.model = model
        initialize()
    }

    fun onMusicSwitchCheckedChange(checked: Boolean) {

        model.putSharedPreferenceMusicActivated(checked)
        if (checked) {
            view.playMusic()
        } else {
            view.stopPlayingMusic()
        }
    }
}