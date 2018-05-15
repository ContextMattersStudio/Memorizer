package com.exgames.exmi.main.memorizer.mvp.presenter

import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.background.BackgroundMusicManager
import com.exgames.exmi.main.memorizer.mvp.model.WelcomeModel
import com.exgames.exmi.main.memorizer.mvp.view.WelcomeView
import com.exgames.exmi.main.memorizer.persistent.preferences.SharedPreferenceRepository

class WelcomePresenter : BasePresenter {

    private var view: WelcomeView? = null
    private var model: WelcomeModel? = null
    private var isMusicActivated: Boolean = false

    private lateinit var musicManager: BackgroundMusicManager

    constructor(view: WelcomeView, model: WelcomeModel) {
        this.view = view
        this.baseView = view
        this.model = model
        init()
    }

    private fun init() {
        initialize()
        isMusicActivated = model!!.getSharedPreferenceIsMusicActivated()
        initMusic()
    }

    private fun initMusic() {
        val sharedPreferenceRepository = SharedPreferenceRepository(view?.activity?.applicationContext!!)
        musicManager = BackgroundMusicManager(view?.activity?.applicationContext!!, R.raw.splashscreenloop, sharedPreferenceRepository)
        musicManager.execute()
    }

    fun goToGame() {
        view?.launchGameActivity()
    }

    fun onExitButtonPressed() {
        view?.onExitButtonPressed()
    }

    fun onPause() {
        musicManager.pauseMusic()
    }

    fun playMusic() {
        if (isMusicActivated) {
            // view!!.playMusic()
        }
    }

    fun stopPlayingMusic() {
        view!!.stopPlayingMusic()
    }

    fun goToHighScoresScreen() {
        view?.launchHighScoresActivity()
    }

    fun goToSettingsScreen() {
        view?.launchSettingsScreen()
    }
}
