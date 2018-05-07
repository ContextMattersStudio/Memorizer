package com.exgames.exmi.main.memorizer.base

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.exgames.exmi.main.utils.ActivityUtils


class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            ActivityUtils.startActivityAndFinish(this, MainActivity.getIntent(this))
        }, SPLASH_DISPLAY_LENGTH)

    }
}