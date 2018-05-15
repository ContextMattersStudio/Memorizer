package com.exgames.exmi.main.memorizer.mvp.view

import android.media.MediaPlayer
import android.widget.CompoundButton
import android.widget.Switch
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.base.SettingsActivity

class SettingsView : BaseView {
    private var mediaPlayer: MediaPlayer?

    constructor(settingsActivity: SettingsActivity) {
        this.activity = settingsActivity
        mediaPlayer = MediaPlayer.create(this.activity!!.applicationContext, R.raw.piglevelwin2)

    }

    /*fun playMusic() {
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
    }*/

    fun setMusicSwitchState(setSwitchOn: Boolean) {
        val switch = activity?.findViewById<Switch>(R.id.switch_music)
        switch?.isChecked = setSwitchOn
    }

    fun setOnMusicSwitchCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener) {
        val switch = activity?.findViewById<Switch>(R.id.switch_music)
        switch?.setOnCheckedChangeListener { buttonView, isChecked ->
            listener.onCheckedChanged(buttonView, isChecked)
        }
    }

    fun setOnSoundsSwitchCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener) {
        val switch = activity?.findViewById<Switch>(R.id.switch_sounds)
        switch?.setOnCheckedChangeListener { buttonView, isChecked ->
            listener.onCheckedChanged(buttonView, isChecked)
        }
    }

    fun setSoundsSwitchState(setSwitchOn: Boolean) {
        val switch = activity?.findViewById<Switch>(R.id.switch_sounds)
        switch?.isChecked = setSwitchOn
    }


}