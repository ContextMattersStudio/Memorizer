package com.exgames.exmi.main.memorizer.mvp.presenter

import com.exgames.exmi.main.bus.RxBusKotlin
import com.exgames.exmi.main.bus.events.base.ShouldPlayMusicBusObserver
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
        initializeConfiguration()
    }

    private fun initializeConfiguration() {
        view.setMusicSwitchState(model.getSharedPreferenceMusicActivated())
        view.setSoundsSwitchState(model.getSharedPreferenceSoundsActivated())
    }

    fun onMusicSwitchCheckedChange(checked: Boolean) {
        if (model.getSharedPreferenceMusicActivated() != checked) {
            model.putSharedPreferenceMusicActivated(checked)
            if (checked) {
                RxBusKotlin.post(ShouldPlayMusicBusObserver.ShouldPlayMusicEvent(true))
            } else {
                RxBusKotlin.post(ShouldPlayMusicBusObserver.ShouldPlayMusicEvent(false))
            }
        }
    }

    fun onSoundsSwitchCheckedChange(checked: Boolean) {
        if (model.getSharedPreferenceSoundsActivated() != checked) {
            model.putSharedPreferenceSoundsActivated(checked)
        }
    }
}