package com.example.munchkin.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.munchkin.models.Player

class PlayerListView(context: Context, attrs: AttributeSet? = null) : RecyclerView(context, attrs) {

    init {
        setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        setLayoutManager(layoutManager)

        val dividerItemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        addItemDecoration(dividerItemDecoration)
    }

}

class PlayerAdapter(private val players: ArrayList<Player>?) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val item = PlayerView(parent.context)
        return PlayerViewHolder(item)
    }

    override fun getItemCount(): Int = players?.size ?: 0

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        players?.let {
            holder.bind(it[position])
        }
    }

}

class PlayerViewHolder(private val view: PlayerView) : RecyclerView.ViewHolder(view) {

    fun bind(player: Player) {
        view.bind(player)
    }

}