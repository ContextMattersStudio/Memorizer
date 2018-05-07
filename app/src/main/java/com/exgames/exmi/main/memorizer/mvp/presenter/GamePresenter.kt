package com.exgames.exmi.main.memorizer.mvp.presenter

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import com.exgames.exmi.main.bus.RxBusKotlin
import com.exgames.exmi.main.bus.events.base.DialogHighScoresSaveButtonPressedBusObserverKotlin
import com.exgames.exmi.main.memorizer.adapters.ImageAdapter
import com.exgames.exmi.main.memorizer.base.BaseActivity
import com.exgames.exmi.main.memorizer.base.HighScoresActivity
import com.exgames.exmi.main.memorizer.base.MainActivity
import com.exgames.exmi.main.memorizer.mvp.model.GameModel
import com.exgames.exmi.main.memorizer.mvp.view.GameView
import com.exgames.exmi.main.utils.ActivityUtils
import com.exgames.exmi.main.utils.AlertDialogUtils
import com.exgames.exmi.main.utils.OnButtonClickedCallback

class GamePresenter : BasePresenter {

    private var view: GameView? = null
    private var model: GameModel? = null
    private var firstClick: Boolean? = true
    private var playing: Boolean? = false
    private var position: Int? = 0
    private var lastTappedCardPosition: Int? = 0
    private var cardsStillInGame = 0

    companion object {
        private const val DELAY = 500L
        private const val DESTROY_DELAY = 0L
    }

    constructor(gameView: GameView, gameModel: GameModel) {
        baseView = gameView
        this.view = gameView
        this.model = gameModel
        initialize()
        loadGameData()
        cardsStillInGame = model?.getCards()!!.size
    }

    private fun loadGameData() {
        loadCards()
        //TODO UNCOMMENT THIS WHEN ON THE MARKET VERSION
        model?.shuffleCards()
        view?.setGridViewAdapter(ImageAdapter(view!!.getActivity(), model!!.getCards()!!))
    }

    private fun loadCards() {
        model?.loadCards()
    }


    fun onItemClickListener(parent: AdapterView<*>?, view: View?, currentCardTappedPosition: Int, id: Long) {
        this.position = currentCardTappedPosition
        if (model == null)
            return
        if (model!!.isCardShowingBack(currentCardTappedPosition) && playing!!.not()) {
            playing = true
            model!!.setTries(model!!.getTries().plus(1))
            model!!.setCardShowingBack(currentCardTappedPosition, false)

            view as ImageView
            view.setImageResource(model!!.getCard(currentCardTappedPosition)!!.getImage())


            if (firstClick!!) {
                lastTappedCardPosition = currentCardTappedPosition
                firstClick = false
                playing = false
            } else {
                // i have two cards clicked at this moment
                if (model!!.getCard(lastTappedCardPosition!!)!!.equals(model!!.getCard(currentCardTappedPosition))) { // Same pair of cards - two equal cards, they have to disappear
                    cardsStillInGame -= 2

                    destroyCardsAfterDelay(lastTappedCardPosition!!, currentCardTappedPosition)
                    if (cardsStillInGame == 0) { // When cards still in game == 0 then WIN!
                        performVictory()
                    }
                } else {
                    delayImagesAfterBothClicked(lastTappedCardPosition!!, currentCardTappedPosition)
                }
                firstClick = true
            }
        }

    }

    private fun destroyCardsAfterDelay(lastTappedCardPosition: Int, currentCardTappedPosition: Int) {
        view?.destroyCardsAfterDelay(lastTappedCardPosition, currentCardTappedPosition, DESTROY_DELAY)
        //handler.postDelayed({ playing = false }, DESTROY_DELAY)
        playing = false

    }

    private fun delayImagesAfterBothClicked(lastTappedCardPosition: Int, currentCardTappedPosition: Int) {
        view?.hideCardsAfterDelay(lastTappedCardPosition, currentCardTappedPosition, Runnable {
            kotlin.run {
                model?.setCardShowingBack(lastTappedCardPosition, true)
                model?.setCardShowingBack(currentCardTappedPosition, true)
                playing = false
            }
        }, DELAY)
    }

    private fun performVictory() {
        performWinSound()
        goToHIghScoreScreen()

    }

    private fun performWinSound() {
        if (model?.isSoundActive()!!) {
            view?.performWinSound()
        }
    }

    private fun goToHIghScoreScreen() {
        val activity: BaseActivity = view?.getActivity() as BaseActivity
        val onButtonBackClickedCallback = object : OnButtonClickedCallback {
            override fun onClick() {
                ActivityUtils.startActivityAndFinishFadeOutFadeIn(activity, MainActivity.getIntent(activity))
            }
        }

        AlertDialogUtils.showAlertDialogSaveHighScore(view?.getActivity()!!,
                onButtonBackClickedCallback,
                model!!.getTries())
    }

    fun onResume() {
        val activity: BaseActivity = view?.getActivity() as BaseActivity
        subscribeSaveHighScoreDialogObserver(activity)
    }

    private fun subscribeSaveHighScoreDialogObserver(activity: Activity) {
        RxBusKotlin.subscribe(this, object : DialogHighScoresSaveButtonPressedBusObserverKotlin() {
            override fun onEvent(value: DialogHighScoresSaveButtonPressedEvent) {
                value.userName
                model!!.saveNewHighScore(userName = value.userName, points = model!!.getTries().toString())
                ActivityUtils.startActivityAndFinishFadeOutFadeIn(activity,
                        HighScoresActivity.getIntent(activity, model!!.getTries(), value.userName))
            }
        })
    }

    fun onPause() {
        RxBusKotlin.clear(this)
    }
}