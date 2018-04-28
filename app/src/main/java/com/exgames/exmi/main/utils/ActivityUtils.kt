package com.exgames.exmi.main.utils

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.exgames.exmi.main.memorizer.R

class ActivityUtils {
    companion object {
        fun startActivity(activity: Activity, intent: Intent) {
            startActivity(activity.applicationContext, intent, null)
        }

        fun startActivityAndFinish(activity: Activity, intent: Intent) {
            startActivity(activity.applicationContext, intent, null)
            activity.finish()
        }

        fun startActivityAndFinishFadeOutFadeIn(activity: Activity, intent: Intent) {
            startActivity(activity.applicationContext, intent, null)
            activity.finish()
            activity.overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
        }

    }

}