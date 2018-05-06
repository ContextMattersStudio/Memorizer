package com.exgames.exmi.main.memorizer.mvp.presenter

import com.exgames.exmi.main.memorizer.mvp.view.BaseView

abstract class BasePresenter{

    protected var baseView : BaseView? = null

    fun initialize(){
        baseView?.hideActionBar()
    }

}