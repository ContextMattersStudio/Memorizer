package com.exgames.exmi.main.memorizer.mvp.model

import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.SharedPreferenceRepository
import com.exgames.exmi.main.memorizer.persistent.domain.HighScores
import com.exgames.exmi.main.memorizer.persistent.repositories.base.interfaces.HighScoresRepository
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.EIGHT_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.ELEVEN_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.FIVE_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.FOUR_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.NINE_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.ONE_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.SEVEN_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.SIX_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.TEN_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.THREE_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.TWELVE_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.TWO_INT
import java.util.*

class GameModel(repository: HighScoresRepository, private val sharedPreferenceRepository: SharedPreferenceRepository, private val maxHighScoresToDisplay: Long) {

    private var cards: ArrayList<Card>? = ArrayList()
    private var tries: Int = 0
    private var repository: HighScoresRepository? = repository

    fun loadCards() {
        cards?.add(Card(ONE_INT, R.drawable.card1))
        cards?.add(Card(ONE_INT, R.drawable.card1))
        cards?.add(Card(TWO_INT, R.drawable.card2))
        cards?.add(Card(TWO_INT, R.drawable.card2))
        cards?.add(Card(THREE_INT, R.drawable.card3))
        cards?.add(Card(THREE_INT, R.drawable.card3))
        cards?.add(Card(FOUR_INT, R.drawable.card4))
        cards?.add(Card(FOUR_INT, R.drawable.card4))
        cards?.add(Card(FIVE_INT, R.drawable.card5))
        cards?.add(Card(FIVE_INT, R.drawable.card5))
        cards?.add(Card(SIX_INT, R.drawable.card6))
        cards?.add(Card(SIX_INT, R.drawable.card6))
        cards?.add(Card(SEVEN_INT, R.drawable.card7))
        cards?.add(Card(SEVEN_INT, R.drawable.card7))
        cards?.add(Card(EIGHT_INT, R.drawable.card8))
        cards?.add(Card(EIGHT_INT, R.drawable.card8))
        cards?.add(Card(NINE_INT, R.drawable.card9))
        cards?.add(Card(NINE_INT, R.drawable.card9))
        cards?.add(Card(TEN_INT, R.drawable.card10))
        cards?.add(Card(TEN_INT, R.drawable.card10))
        cards?.add(Card(ELEVEN_INT, R.drawable.card11))
        cards?.add(Card(ELEVEN_INT, R.drawable.card11))
        cards?.add(Card(TWELVE_INT, R.drawable.card12))
        cards?.add(Card(TWELVE_INT, R.drawable.card12))

    }

    fun getCards(): ArrayList<Card>? {
        return cards
    }

    fun getCard(position: Int): Card? {
        return cards?.get(position)
    }

    fun isCardShowingBack(position: Int): Boolean {
        return cards!![position].isShowingBack()
    }

    fun setCardShowingBack(position: Int, isShowingBack: Boolean) {
        cards!![position].setCardShowingBack(isShowingBack)
    }

    fun shuffleCards() {
        Collections.shuffle(cards)
    }

    fun setTries(tries: Int) {
        this.tries = tries
    }

    fun getTries() = tries

    fun saveNewHighScore(userName: String, points: String) {
        if (repository?.getCount() == maxHighScoresToDisplay) {
            repository?.clearLast()
        }
        repository?.saveHighScore(HighScores(userName, points))
    }

    fun getAllHighScores(): MutableList<HighScores>? {
        return repository?.getAllHighScores()
    }

    fun getFirstHighScore(): HighScores? {
        return repository?.getFirst()
    }

    fun isSoundActive(): Boolean {
        return sharedPreferenceRepository.getSound()
    }
}