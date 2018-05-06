package com.exgames.exmi.main.memorizer.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.SharedPreferenceRepository
import com.exgames.exmi.main.memorizer.mvp.model.WelcomeModel
import com.exgames.exmi.main.memorizer.mvp.presenter.WelcomePresenter
import com.exgames.exmi.main.memorizer.mvp.view.WelcomeView
import com.google.android.gms.ads.MobileAds
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var presenter: WelcomePresenter? = null

    companion object {
        fun getIntent(activity: Activity): Intent {

            return Intent(
                    activity,
                    MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID))
        val config = RealmConfiguration.Builder().name("high_scores.realm").build()

        val sharedPreferenceRepository = SharedPreferenceRepository(this)
        presenter = WelcomePresenter(WelcomeView(this), WelcomeModel(sharedPreferenceRepository))

        onHighScoreIconPressed()
        onHighScoreTextPressed()
        onStartButtonPressed()
        onExitButtonPressed()
        //onMusicCheckboxClick()
        onOptionButtonPressed()

    }

    private fun onOptionButtonPressed() {
        optionsButton.setOnClickListener {
            presenter?.goToSettingsScreen()
        }
    }

    private fun onMusicCheckboxClick() {
        music_checkbox.setOnCheckedChangeListener { _, isChecked ->
            presenter!!.onMusicCheckboxClick(isChecked)
        }
    }

    private fun onExitButtonPressed() {
        exitButton.setOnClickListener {
            shouldStopMusic = false
            presenter?.onExitButtonPressed()
        }
    }

    private fun onHighScoreIconPressed() {
        high_scores_icon.setOnClickListener {
            presenter?.goToHighScoresScreen()
        }
    }

    private fun onHighScoreTextPressed() {
        high_scores_text.setOnClickListener {
            presenter?.goToHighScoresScreen()
        }
    }

    private fun onStartButtonPressed() {
        goGameButton.setOnClickListener {
            //shouldStopMusic = false
            presenter!!.goToGame()
        }
    }

    fun checkMusicCheckBox(checkState: Boolean) {
        music_checkbox.isChecked = checkState
    }

    override fun onResume() {
        super.onResume()
        //shouldStopMusic = true
        //presenter!!.playMusic()
    }

    override fun onPause() {
        super.onPause()
        /*if (shouldStopMusic) {
            presenter!!.stopPlayingMusic()
        }*/
        //presenter?.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
