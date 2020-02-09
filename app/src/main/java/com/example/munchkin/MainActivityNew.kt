package com.example.munchkin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.munchkin.custom_views.PlayerView

class MainActivityNew : AppCompatActivity() {

    private val TAG: String = MainActivityNew::class.java.simpleName

    private lateinit var playersView: ListView
    private lateinit var adapter: ArrayAdapter<PlayerView>
    private lateinit var players: ArrayList<PlayerView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        setSupportActionBar(findViewById(R.id.action_bar))

        playersView = findViewById(R.id.player_list)
        players = ArrayList()
        adapter = ArrayAdapter(this, R.layout.player_view, players)
        playersView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_player -> {
            addPlayer()
            true
        }
        R.id.remove_player -> {
            removePlayer()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun addPlayer() {
//        players.add(PlayerView(this, null))
//        adapter.notifyDataSetChanged()
    }

    private fun removePlayer() {
//        players.removeAt(players.size - 1)
//        adapter.notifyDataSetChanged()
    }

}
