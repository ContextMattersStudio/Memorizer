package com.exgames.exmi.main.memorizer.mvp.view

import android.annotation.SuppressLint
import android.content.Context.AUDIO_SERVICE
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.os.Handler
import android.util.TypedValue
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.adapters.ImageAdapter
import com.exgames.exmi.main.memorizer.base.GameActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class GameView : BaseView {

    private var gridViewAdapter: ImageAdapter? = null
    private var mediaPlayer: MediaPlayer?
    private lateinit var banner: AdView
    private lateinit var soundPool: SoundPool
    private var volume: Float = 0.0f
    private var cardDisappearSound = R.raw.carddisappear
    private var cardTapSound = R.raw.cardtap
    private val SOUNDS_VOLUME: Float = 0.2F
    private val MAX_STREAM_AUDIOS = 10


    companion object {
        private const val VOLUME = 0.5f
    }


    constructor(gameActivity: GameActivity) {
        this.activity = gameActivity
        mediaPlayer = MediaPlayer.create(this.activity!!.applicationContext, R.raw.piglevelwin2)
        initBannerAd()
        initSoundPool()
    }

    @SuppressLint("InlinedApi")
    private fun initSoundPool() {
        var audioManager = getActivity().getSystemService(AUDIO_SERVICE) as AudioManager
        var actVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
        var maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        volume = actVolume / maxVolume

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            soundPool = SoundPool.Builder()
                    .setMaxStreams(MAX_STREAM_AUDIOS)
                    .setAudioAttributes(audioAttributes)
                    .build()
        } else {
            soundPool = SoundPool(MAX_STREAM_AUDIOS, AudioManager.STREAM_MUSIC, 0)
        }



        cardDisappearSound = soundPool.load(getActivity(), R.raw.carddisappear, 1)
        cardTapSound = soundPool.load(getActivity(), R.raw.cardtap, 1)
        soundPool.setVolume(cardDisappearSound, volume, volume)
        soundPool.setVolume(cardTapSound, volume, volume)
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

    fun playCardDisappearSound() {
        soundPool.play(cardDisappearSound, SOUNDS_VOLUME, SOUNDS_VOLUME, 0, 0, 1f)
    }

    fun playCardTapSound() {
        soundPool.play(cardTapSound, SOUNDS_VOLUME, SOUNDS_VOLUME, 0, 0, 1f)
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

    fun getFloatResource(floatID: Int): Float {
        val outValue = TypedValue()
        var result: Float = 0F
        activity?.resources?.getValue(floatID, outValue, true)
        if (!outValue.float.isNaN()) {
            result = outValue.float
        }
        return result
    }
}