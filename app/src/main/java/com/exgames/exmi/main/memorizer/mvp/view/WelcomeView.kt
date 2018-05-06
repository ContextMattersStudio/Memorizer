package com.exgames.exmi.main.memorizer.mvp.view

import android.media.MediaPlayer
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.base.GameActivity
import com.exgames.exmi.main.memorizer.base.HighScoresActivity
import com.exgames.exmi.main.memorizer.base.MainActivity
import com.exgames.exmi.main.memorizer.base.SettingsActivity
import com.exgames.exmi.main.utils.ActivityUtils

class WelcomeView : BaseView {

    private var mediaPlayer: MediaPlayer? = null

    constructor(activity: MainActivity) {
        this.activity = activity

        mediaPlayer = MediaPlayer.create(activity.baseContext, R.raw.splashscreenloop)
    }

    fun launchGameActivity() {
        ActivityUtils.startActivityAndFinishFadeOutFadeIn(activity!!, GameActivity.getIntent(activity!!))
    }

    fun onExitButtonPressed() {
        mediaPlayer?.pause()
        mediaPlayer?.release()
        activity?.finish()
    }

    fun releaseMediaPlayer() {
        mediaPlayer?.release()
    }

    fun playMusic() {
        if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {
            mediaPlayer = MediaPlayer.create(activity?.baseContext, R.raw.splashscreenloop)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    fun stopPlayingMusic() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.pause()
            mediaPlayer!!.seekTo(0)
        }
    }

    fun checkCheckBox() {
        val activityMain: MainActivity = activity as MainActivity
        activityMain.checkMusicCheckBox(true)
    }

    fun uncheckCheckBox() {
        val activityMain: MainActivity = activity as MainActivity
        activityMain.checkMusicCheckBox(false)
    }

    fun launchHighScoresActivity() {
        ActivityUtils.startActivityAndFinishFadeOutFadeIn(activity!!, HighScoresActivity.getIntent(activity!!))
    }

    fun launchSettingsScreen() {
        ActivityUtils.startActivityAndFinishFadeOutFadeIn(activity!!, SettingsActivity.getIntent(activity!!))
    }

}
