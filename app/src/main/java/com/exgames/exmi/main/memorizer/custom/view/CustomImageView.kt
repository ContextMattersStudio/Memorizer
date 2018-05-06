package com.exgames.exmi.main.memorizer.custom.view

import android.content.Context
import android.widget.ImageView


class CustomImageView(context: Context?) : ImageView(context) {
/*
    init {
        this.onFinishInflate()
    }
    override fun destroyDrawingCache() {
        super.destroyDrawingCache()


/*imageView.animate()
                    .scaleXBy(1.5f)
                    .setDuration(500)
                    .start()*/
        this.animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationEnd(animation: Animation?) {
                    this. = View.GONE
                   imageView.destroyDrawingCache()
            }

            override fun onAnimationStart(animation: Animation?) {
                //To change body of created functions use File | Settings | File Templates.
            }
        }
    }*/
}