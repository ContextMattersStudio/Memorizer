package com.exgames.exmi.main.memorizer.base

import android.app.Activity
import android.app.AlertDialog
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

    companion object {
        @JvmField
        val EXTRA_POINTS = "EXTRA_POINTS"
        val EXTRA_USER_NAME = "EXTRA_USER_NAME"

        fun getIntent(activity: Activity): Intent {

            return Intent(
                    activity,
                    HighScoresActivity::class.java)
        }

        fun getIntent(activity: Activity, points: Int, userName: String): Intent {

            var intent = Intent(activity, HighScoresActivity::class.java)
            intent.putExtra(EXTRA_POINTS, points)
            intent.putExtra(EXTRA_USER_NAME, userName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)
        if (intent.extras != null) {
            val lastUserPoints: Int = intent.getIntExtra(EXTRA_POINTS, -1)
            val lastUserName: String = intent.getStringExtra(EXTRA_USER_NAME)
        }
        val highScoresRepository: HighScoresRepository = HighScoresRepositoryImpl(null, RHighscoresDataSource(), RHighScoresMapper())

        presenter = HighScoresPresenter(HighScoresView(this), HighScoresModel(highScoresRepository))


        //TODO: guardar estos puntos en la bd de los high_scores y permitir consultarla desde la primer pantalla
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
            ActivityUtils.startActivityAndFinishFadeOutFadeIn(this, MainActivity.getIntent(this))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityUtils.startActivityAndFinishFadeOutFadeIn(this, GameActivity.getIntent(this))
    }
}
