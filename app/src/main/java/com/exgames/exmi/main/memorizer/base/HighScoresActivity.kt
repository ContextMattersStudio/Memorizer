package com.exgames.exmi.main.memorizer.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.mvp.model.HighScoresModel
import com.exgames.exmi.main.memorizer.mvp.presenter.HighScoresPresenter
import com.exgames.exmi.main.memorizer.mvp.view.HighScoresView
import com.exgames.exmi.main.memorizer.persistent.data_source.RHighscoresDataSource
import com.exgames.exmi.main.memorizer.persistent.mappers.RHighScoresMapper
import com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces.HighScoresRepository
import com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces.implementations.HighScoresRepositoryImpl
import com.exgames.exmi.main.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_high_scores.*

class HighScoresActivity : BaseActivity() {

    private var presenter: HighScoresPresenter? = null
    private var screenToGoBackTo: String = ""

    companion object {
        @JvmField
        val EXTRA_POINTS = "EXTRA_POINTS"
        val EXTRA_COMES_FROM_WELCOME_SCREEN = "EXTRA_COMES_FROM_WELCOME_SCREEN"
        val EXTRA_COMES_FROM_GAME_SCREEN = "EXTRA_COMES_FROM_GAME_SCREEN"
        val EXTRA_COMES_FROM_SCREEN = "EXTRA_COMES_FROM_SCREEN"
        val NO_USER_POINTS = -1

        fun getIntent(activity: Activity): Intent {
            val intent = Intent(activity, HighScoresActivity::class.java)
            intent.putExtra(EXTRA_COMES_FROM_SCREEN, EXTRA_COMES_FROM_WELCOME_SCREEN)
            return intent
        }

        fun getIntent(activity: Activity, points: Int): Intent {
            val intent = Intent(activity, HighScoresActivity::class.java)
            intent.putExtra(EXTRA_POINTS, points)
            intent.putExtra(EXTRA_COMES_FROM_SCREEN, EXTRA_COMES_FROM_GAME_SCREEN)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)
        val lastUserPoints: Int = intent.getIntExtra(EXTRA_POINTS, NO_USER_POINTS)
        if (intent.extras != null) {
            if (intent.hasExtra(EXTRA_COMES_FROM_SCREEN)) {
                screenToGoBackTo = intent.getStringExtra(EXTRA_COMES_FROM_SCREEN)
            }
        }
        val highScoresRepository: HighScoresRepository = HighScoresRepositoryImpl(null, RHighscoresDataSource(), RHighScoresMapper())

        presenter = HighScoresPresenter(HighScoresView(this), HighScoresModel(highScoresRepository))
        if (lastUserPoints != NO_USER_POINTS) {
            presenter!!.initializeLastGamePointsLabel(lastUserPoints)
        }else{
            presenter!!.hideLastGamePointsLabel()
        }
        onClearRecordsButtonPressed()
        onPlayAgainButtonPressed()
        onReturnButtonPressed()
    }

    private fun onClearRecordsButtonPressed() {
        clear_records.setOnClickListener {
            presenter?.onClearRecordsButtonPressed()
        }

    }

    private fun onPlayAgainButtonPressed() {
        play_again_button2.setOnClickListener {
            ActivityUtils.startActivityAndFinishFadeOutFadeIn(this, GameActivity.getIntent(this))
        }

    }

    private fun onReturnButtonPressed() {
        return_button2.setOnClickListener {
            decideWhereToGoBack()
        }
    }

    private fun decideWhereToGoBack() {
        if (screenToGoBackTo == EXTRA_COMES_FROM_WELCOME_SCREEN) {
            ActivityUtils.startActivityAndFinishFadeOutFadeIn(this, MainActivity.getIntent(this))
        } else if (screenToGoBackTo == EXTRA_COMES_FROM_GAME_SCREEN) {
            ActivityUtils.startActivityAndFinishFadeOutFadeIn(this, MainActivity.getIntent(this))
        }
    }

    override fun onBackPressed() {
        decideWhereToGoBack()
    }
}
