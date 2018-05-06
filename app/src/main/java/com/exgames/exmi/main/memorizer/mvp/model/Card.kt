package com.exgames.exmi.main.memorizer.mvp.model

import com.exgames.exmi.main.memorizer.R

class Card(private var id: Int, private var imageFront: Int) {

    private var showingBack = true
    var visible = true
    var alreadyGone = false

    override fun equals(other: Any?): Boolean {
        var card: Card = other as Card
        return card.id.equals(this.id)
    }

    fun getImage(): Int {
        if (showingBack) {
            return R.drawable.back
        }
        return imageFront
    }

    fun isShowingBack(): Boolean {
        return showingBack
    }

    fun setCardShowingBack(showingBack: Boolean) {
        this.showingBack = showingBack
    }

}
