package com.exgames.exmi.main.memorizer.mvp.presenter

import android.app.AlertDialog
import com.exgames.exmi.main.memorizer.mvp.model.HighScoresModel
import com.exgames.exmi.main.memorizer.mvp.view.HighScoresView
import com.exgames.exmi.main.memorizer.persistent.domain.HighScores
import com.exgames.exmi.main.utils.OnButtonClickedCallback

class HighScoresPresenter(var view: HighScoresView, var model: HighScoresModel) : BasePresenter() {

    private val ELEMENTS_TO_SHOW = 10

    init {
        this.baseView = view
        loadListWithItems(model.getTopXElements(ELEMENTS_TO_SHOW))
        initialize()
    }

    private fun loadListWithItems(allHighScores: MutableList<HighScores>) {
        //TODO CARGAR LA RECYCLERVIEW CON LOS 10 MEJORES RECORDS DE UNA FORMA COOL Y ANIMADA XQ SOY EL MEJOR 8-D
        (baseView as HighScoresView).setRecyclerViewItems(allHighScores)
    }

    fun onClearRecordsButtonPressed() {
        (baseView as HighScoresView).showSureToClearRecordsDialog(getOnButtonYesPressedCallback(),getOnButtonNoPressedCallback())
    }

    private fun getOnButtonYesPressedCallback(): OnButtonClickedCallback = object : OnButtonClickedCallback{
        override fun onClick() {
            model.clearRecords()
            (baseView as HighScoresView).setRecyclerViewItems(model.getAllHighScores())
            view.notifySetDataChanged()
        }

    }

    private fun getOnButtonNoPressedCallback(): OnButtonClickedCallback = object : OnButtonClickedCallback{
        override fun onClick() {
            //Do nothing (Dismiss dialog)
        }

    }
}

