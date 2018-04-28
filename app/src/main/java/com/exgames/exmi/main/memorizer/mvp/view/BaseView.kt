package com.exgames.exmi.main.memorizer.mvp.view

import android.app.Activity
import com.exgames.exmi.main.memorizer.base.BaseActivity
import java.lang.ref.WeakReference
import java.sql.Ref

//TODO: CAMBIAR TODO POR WEAK REFERENCE
//open class BaseView<T : Activity> {
open class BaseView {

    //val ref: WeakReference<T>? = null
    var activity: BaseActivity? = null

    fun hideActionBar() {
        activity?.hideActionBar()
    }


}
