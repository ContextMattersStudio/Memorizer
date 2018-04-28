package com.exgames.exmi.main.memorizer.mvp.view

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.adapters.HighScoresAdapter
import com.exgames.exmi.main.memorizer.base.BaseActivity
import com.exgames.exmi.main.memorizer.persistent.domain.HighScores
import com.exgames.exmi.main.utils.OnButtonClickedCallback

class HighScoresView(activity: BaseActivity) : BaseView() {
    private var recyclerView: RecyclerView? = null
    private var adapter: HighScoresAdapter? = null

    init {
        this.activity = activity
        initRecyclerView()
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
        val dialog: AlertDialog = builder.setTitle("Delete all records?").setMessage("This action can not be undone")
                .setPositiveButton("Confirm", { _, _ ->
                    onButtonYesPressedCallback.onClick()
                })
                .setNegativeButton("Cancel", { _, _ ->
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
}