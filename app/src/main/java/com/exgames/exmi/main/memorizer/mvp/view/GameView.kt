package com.exgames.exmi.main.memorizer.mvp.view

import android.media.MediaPlayer
import android.os.Handler
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.base.GameActivity
import com.exgames.exmi.main.memorizer.adapters.ImageAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class GameView : BaseView {

    private var gridViewAdapter: ImageAdapter? = null
    private var mediaPlayer: MediaPlayer?
    private lateinit var banner: AdView

    companion object {
        private const val VOLUME = 0.5f
    }


    constructor(gameActivity: GameActivity) {
        this.activity = gameActivity
        mediaPlayer = MediaPlayer.create(this.activity!!.applicationContext, R.raw.piglevelwin2)
        initBannerAd()
    }

    private fun initBannerAd() {
        banner = activity!!.findViewById(R.id.admob_ad_game)
        val adRequest = AdRequest.Builder().build()
        banner.loadAd(adRequest)
    }

    fun setGridViewAdapter(gridViewAdapter: ImageAdapter) {
        this.gridViewAdapter = gridViewAdapter
        getActivity().setGridViewAdapter(gridViewAdapter)
    }

    fun getActivity(): GameActivity {
        return activity as GameActivity
    }

    fun performWinSound() {
        mediaPlayer?.setVolume(VOLUME, VOLUME)
        mediaPlayer?.start()
    }

    fun hideCardsAfterDelay(lastTappedCardPosition: Int, currentCardTappedPosition: Int, runnable: Runnable, delay: Long) {

        Handler().postDelayed({
            gridViewAdapter!!.getItem(lastTappedCardPosition)!!.setCardShowingBack(true)
            gridViewAdapter!!.getItem(currentCardTappedPosition)!!.setCardShowingBack(true)
            runnable.run()
            gridViewAdapter!!.notifyDataSetChanged()
        }, delay)

    }

    fun destroyCardsAfterDelay(lastTappedCardPosition: Int, currentCardTappedPosition: Int, delay: Long) {
        Handler().postDelayed({
            gridViewAdapter!!.removeCardAt(lastTappedCardPosition)
            gridViewAdapter!!.removeCardAt(currentCardTappedPosition)
            gridViewAdapter!!.notifyDataSetChanged()
        }, delay)

    }
}