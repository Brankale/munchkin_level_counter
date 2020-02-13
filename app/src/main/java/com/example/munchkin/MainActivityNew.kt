package com.example.munchkin

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.munchkin.custom_views.PlayerAdapter
import com.example.munchkin.custom_views.PlayerListView
import com.example.munchkin.models.Player

class MainActivityNew : AppCompatActivity() {

    private val TAG: String = MainActivityNew::class.java.simpleName

    lateinit var playerListView: PlayerListView
    lateinit var playerAdapter: PlayerAdapter
    private val playerList = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        setSupportActionBar(findViewById(R.id.action_bar))

        playerListView = findViewById(R.id.player_list)
        playerAdapter = PlayerAdapter(playerList)
        playerListView.adapter = playerAdapter
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
        R.id.new_game -> {
            AlertDialog.Builder(this)
                    .setMessage(R.string.dialog_new_game)
                    .setPositiveButton(android.R.string.yes,
                            DialogInterface.OnClickListener { dialog, which -> newGame() })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()

            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun addPlayer() {
        playerList.add(Player())
        // TODO: don't refresh all items
        playerAdapter.notifyDataSetChanged()
    }

    private fun removePlayer() {
        if (playerList.isNotEmpty()) {
            playerList.removeAt(playerList.size-1)
            // TODO: don't refresh all items
            playerAdapter.notifyDataSetChanged()
        }
    }

    private fun newGame() {
        playerList.clear()
        // TODO: don't refresh all items
        playerAdapter.notifyDataSetChanged()
    }

}
