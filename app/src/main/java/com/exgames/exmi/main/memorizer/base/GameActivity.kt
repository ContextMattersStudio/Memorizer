package com.exgames.exmi.main.memorizer.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.adapters.ImageAdapter
import com.exgames.exmi.main.memorizer.mvp.model.GameModel
import com.exgames.exmi.main.memorizer.mvp.presenter.GamePresenter
import com.exgames.exmi.main.memorizer.mvp.view.GameView
import com.exgames.exmi.main.memorizer.persistent.data_source.RHighscoresDataSource
import com.exgames.exmi.main.memorizer.persistent.mappers.RHighScoresMapper
import com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces.HighScoresRepository
import com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces.implementations.HighScoresRepositoryImpl
import com.exgames.exmi.main.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : BaseActivity() {

    private var presenter: GamePresenter? = null

    companion object {
        fun getIntent(activity: Activity): Intent {

            return Intent(
                    activity,
                    GameActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val highScoresRepository: HighScoresRepository = HighScoresRepositoryImpl(null, RHighscoresDataSource(), RHighScoresMapper())

        presenter = GamePresenter(GameView(this), GameModel(highScoresRepository))

        onReturnButtonPressed()
        onPlayAgainButtonPressed()
        onGridViewClicked()

    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    private fun onGridViewClicked() {
        grid_view.setOnItemClickListener { parent, view, position, id ->
            presenter?.onItemClickListener(parent, view, position, id)
        }

    }

    private fun onPlayAgainButtonPressed() {
        play_again_button.setOnClickListener {
            recreate()
        }
    }

    private fun onReturnButtonPressed() {
        return_button.setOnClickListener {
            ActivityUtils.startActivityAndFinishFadeOutFadeIn(this, MainActivity.getIntent(this))
        }

    }

    fun setGridViewAdapter(gridViewAdapter: ImageAdapter) {
        grid_view.adapter = gridViewAdapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityUtils.startActivityAndFinishFadeOutFadeIn(this, MainActivity.getIntent(this))
    }
}
