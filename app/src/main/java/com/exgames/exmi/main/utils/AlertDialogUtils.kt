package com.exgames.exmi.main.utils

import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.exgames.exmi.main.bus.RxBusKotlin
import com.exgames.exmi.main.bus.events.base.DialogHighScoresSaveButtonPressedBusObserverKotlin
import com.exgames.exmi.main.memorizer.R
import java.lang.ref.WeakReference


class AlertDialogUtils {
    companion object {
        fun showAlertDialogSaveHighScore(activity: Activity,
                                         onButtonBackClicked: OnButtonClickedCallback,
                                         newHighScore: Int) {
            val dialog = createDialog(activity, R.layout.new_high_score_dialog, false)
            dialog!!.show()
            val buttonBack: Button = dialog.findViewById(R.id.button_back)
            val buttonSave: Button = dialog.findViewById(R.id.button_save)
            val editHighScoresUser: EditText = dialog.findViewById(R.id.edit_high_score_user)
            val labelNewHighScoreNumber: TextView = dialog.findViewById(R.id.high_score_number)
            labelNewHighScoreNumber.text = newHighScore.toString()
            buttonBack.setOnClickListener {
                onButtonBackClicked.onClick()
                dialog.dismiss()
            }
            buttonSave.setOnClickListener {
                RxBusKotlin.post(DialogHighScoresSaveButtonPressedBusObserverKotlin.DialogHighScoresSaveButtonPressedEvent(editHighScoresUser.text.toString()))
                dialog.dismiss()
            }
        }

        fun showAlertDialogSaveHighScoreExample(activity: Activity,
                                                onButtonBackClicked: OnButtonClickedCallback,
                                                onButtonSaveClicked: OnButtonClickedCallback) {
            val dialog = createDialog(activity, R.layout.new_high_score_dialog, false)
            dialog!!.show()
            val buttonBack: Button = dialog.findViewById(R.id.button_back)
            val buttonSave: Button = dialog.findViewById(R.id.button_save)
            val editHighScoresUser: EditText = dialog.findViewById(R.id.edit_high_score_user)
            buttonBack.setOnClickListener {
                onButtonBackClicked.onClick()
                dialog.dismiss()
            }
            buttonSave.setOnClickListener {
                onButtonSaveClicked.onClick()
                dialog.dismiss()
            }
        }


        private fun createDialog(activity: Activity, layoutID: Int, isCancelable: Boolean): AlertDialog? {
            val reference: WeakReference<Activity>? = WeakReference(activity)
            if (reference != null && reference.get()?.isDestroyed == false) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder.setView(layoutID)
                    builder.setCancelable(isCancelable)
                    return builder.create()
                }
            }
            return null
        }
    }
}

interface OnButtonClickedCallback {
    fun onClick()
}