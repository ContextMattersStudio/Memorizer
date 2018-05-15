package com.exgames.exmi.main.memorizer.mvp.view

import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.adapters.HighScoresAdapter
import com.exgames.exmi.main.memorizer.base.BaseActivity
import com.exgames.exmi.main.memorizer.persistent.domain.HighScores
import com.exgames.exmi.main.utils.OnButtonClickedCallback
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class HighScoresView(activity: BaseActivity) : BaseView() {
    private var recyclerView: RecyclerView? = null
    private var adapter: HighScoresAdapter? = null
    private lateinit var banner: AdView

    init {
        this.activity = activity
        initRecyclerView()
        initBannerAd()
    }

    private fun initBannerAd() {
        banner = activity!!.findViewById(R.id.admob_ad_high_scores)
        val adRequest = AdRequest.Builder().build()
        banner.loadAd(adRequest)
    }

    private fun initRecyclerView() {
        recyclerView = activity?.findViewById(R.id.high_score_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(activity?.baseContext, LinearLayout.VERTICAL, false)
    }

    fun setRecyclerViewItems(list: MutableList<HighScores>) {
        if (adapter == null) {
            adapter = HighScoresAdapter(list)
            recyclerView?.adapter = adapter
        } else {
            adapter!!.setItems(list)
            adapter!!.notifyDataSetChanged()
        }
    }

    fun showSureToClearRecordsDialog(onButtonYesPressedCallback: OnButtonClickedCallback, onButtonNoPressedCallback: OnButtonClickedCallback) {
        val builder = AlertDialog.Builder(activity!!)
        val dialog: AlertDialog = builder.setTitle(activity!!.getString(R.string.dialog_confirm_clear_records_title))
                .setMessage(activity!!.getString(R.string.dialog_confirm_clear_records_message))
                .setPositiveButton(activity!!.getString(R.string.dialog_confirm_clear_records_accept_button_text), { _, _ ->
                    onButtonYesPressedCallback.onClick()
                })
                .setNegativeButton(activity!!.getString(R.string.dialog_confirm_clear_records_cancel_button_text), { _, _ ->
                    onButtonNoPressedCallback.onClick()
                    //dialog?.dismiss()
                })
                .setCancelable(true)
                .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(activity!!.resources.getColor(R.color.black_overlay))
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(activity!!.resources.getColor(R.color.black_overlay))
        }
        dialog.show()
    }

    fun notifySetDataChanged() {
        adapter?.notifyDataSetChanged()
    }

    fun initializeLastGamePointsLabel(lastUserPoints: Int) {
        val editText = activity?.findViewById<TextView>(R.id.last_score) as TextView
        editText.text = editText.text.toString().plus(lastUserPoints)
    }

    fun hideLastGamePointsLabel() {
        val editText = activity?.findViewById<TextView>(R.id.last_score) as TextView
        editText.visibility = View.GONE
    }
}