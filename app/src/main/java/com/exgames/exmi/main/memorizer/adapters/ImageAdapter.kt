package com.exgames.exmi.main.memorizer.adapters

import android.content.Context
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.BaseAdapter
import android.widget.ImageView
import com.exgames.exmi.main.memorizer.mvp.model.Card
import com.exgames.exmi.main.utils.AnimationUtils.Companion.DESTROY_CARD_DELAY
import com.exgames.exmi.main.utils.AnimationUtils.Companion.SCALE_CARD_FACTOR
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.EIGHT_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.ONE_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.TWO_INT
import com.exgames.exmi.main.utils.ConstantNumbersUtils.Companion.ZERO_INT


class ImageAdapter(context: Context, cards: ArrayList<Card>) : BaseAdapter() {

    private var context: Context? = context
    private var cards: ArrayList<Card>? = cards
    private var handler: Handler? = Handler()

    init {
        this.context = context
        this.cards = cards
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var imageView = ImageView(context)
        var resolution = getScreenResolution(context!!)

        val width = parent?.width
        val height = parent?.height

        if (resolution[ZERO_INT] <= 480 && resolution[ONE_INT] <= 800) {
            imageView.layoutParams = ViewGroup.LayoutParams(60, 60)//480x800
        } else {
            imageView.layoutParams = ViewGroup.LayoutParams(width!! / 6, (height!! / 6) - 10)//others
        }

        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setPadding(EIGHT_INT, EIGHT_INT, EIGHT_INT, EIGHT_INT)
        if (cards?.get(position)!!.visible) {
            imageView.setImageResource(cards?.get(position)!!.getImage())
        } else {
            if (!(cards?.get(position)!!.alreadyGone)) {
                imageView.setImageResource(cards?.get(position)!!.getImage())
                imageView.animate()
                        .scaleXBy(SCALE_CARD_FACTOR)
                        .scaleYBy(SCALE_CARD_FACTOR)
                        .setDuration(DESTROY_CARD_DELAY)
                        .start()
                handler?.postDelayed(
                        {
                            imageView.visibility = View.GONE
                            imageView.destroyDrawingCache()
                        },
                        DESTROY_CARD_DELAY
                )
                cards?.get(position)!!.alreadyGone = true
            } else {
                imageView.visibility = View.GONE
                imageView.destroyDrawingCache()
            }
        }
        return imageView
    }

    override fun getItem(position: Int): Card? {
        return cards?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return cards?.size!!
    }

    private fun getScreenResolution(context: Context): IntArray {
        val widthAndHeight = IntArray(TWO_INT)
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        widthAndHeight[ZERO_INT] = width
        widthAndHeight[ONE_INT] = height
        return widthAndHeight
    }

    fun removeCardAt(position: Int) {
        cards?.get(position)!!.visible = false
    }

}