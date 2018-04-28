package com.exgames.exmi.main.memorizer.base

import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){

    protected var shouldStopMusic : Boolean = false

    fun hideActionBar() {
        supportActionBar?.hide()
    }
}
