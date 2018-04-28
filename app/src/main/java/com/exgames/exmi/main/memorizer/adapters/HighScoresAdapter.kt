package com.exgames.exmi.main.memorizer.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.exgames.exmi.main.memorizer.R
import com.exgames.exmi.main.memorizer.persistent.domain.HighScores


class HighScoresAdapter(private val userList: MutableList<HighScores>) : RecyclerView.Adapter<HighScoresAdapter.HighScoresViewHolder>() {

    private var userListInternal: MutableList<HighScores> = userList

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HighScoresViewHolder {
        return HighScoresViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.high_score_rv_item, parent, false))
    }

    override fun getItemCount(): Int = userListInternal.size

    override fun onBindViewHolder(holder: HighScoresViewHolder?, position: Int) {
        val highScores = userListInternal[position]
        holder?.userName?.text = highScores.userName
        holder?.score?.text = highScores.score
    }

    fun setItems(listOfNewItems: MutableList<HighScores>) {
        userListInternal.clear()
        userListInternal = listOfNewItems

    }

    class HighScoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById<TextView>(R.id.user_name) as TextView
        val score: TextView = itemView.findViewById<TextView>(R.id.score) as TextView
    }
}
