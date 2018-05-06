package com.exgames.exmi.main.memorizer.mvp.presenter

import com.exgames.exmi.main.memorizer.mvp.model.WelcomeModel
import com.exgames.exmi.main.memorizer.mvp.view.WelcomeView

class WelcomePresenter : BasePresenter{

    private var view: WelcomeView? = null
    private var model: WelcomeModel? = null
    private var isSoundActivated: Boolean = false

    constructor(view: WelcomeView, model: WelcomeModel) {
        this.view = view
        this.baseView = view
        this.model = model
        init()
    }

    private fun init() {
        initialize()
        isSoundActivated = model!!.getShardPreferenceSoundActivated()
        //onMusicCheckboxClick(isSoundActivated)
        //initSound()
    }

    private fun initSound() {
        if (isSoundActivated) {
            view!!.checkCheckBox()
        } else {
            view!!.uncheckCheckBox()
        }
    }

    fun goToGame() {
        view?.launchGameActivity()
    }

    fun onExitButtonPressed() {
        view?.onExitButtonPressed()
    }

    fun onPause(){
        view?.releaseMediaPlayer()
    }


    fun onMusicCheckboxClick(checked: Boolean) {
        model?.putSharedPreferenceSoundActivated(checked)
        if (checked) {
            isSoundActivated = true
            //view!!.playMusic()
        } else {
            isSoundActivated = false
            //view!!.stopPlayingMusic()
        }
    }

    fun playMusic() {
        if (isSoundActivated) {
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
