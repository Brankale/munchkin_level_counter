package com.example.munchkin

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.munchkin.custom_views.PlayerAdapter
import com.example.munchkin.custom_views.PlayerListView
import com.example.munchkin.models.Player

class MainActivityNew : AppCompatActivity() {

    companion object {
        private const val MIN_PLAYERS = 3
        private const val MAX_PLAYERS = 6
    }

    private val TAG: String = MainActivityNew::class.java.simpleName

    lateinit var playerListView: PlayerListView
    lateinit var playerAdapter: PlayerAdapter
    private val playerList = ArrayList<Player>(MIN_PLAYERS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        setSupportActionBar(findViewById(R.id.action_bar))

        playerListView = findViewById(R.id.player_list)
        playerAdapter = PlayerAdapter(playerList)
        playerListView.adapter = playerAdapter

        // add minimum number of players
        for (i in 0 until MIN_PLAYERS) {
            addPlayer()
        }
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
        if (playerList.size < MAX_PLAYERS) {
            playerList.add(Player())
            // TODO: don't refresh all items
            playerAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, R.string.max_players, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removePlayer() {
        if (playerList.size > MIN_PLAYERS) {
            playerList.removeAt(playerList.size-1)
            // TODO: don't refresh all items
            playerAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, R.string.min_players, Toast.LENGTH_SHORT).show()
        }
    }

    private fun newGame() {
        for (player in playerList) {
            player.resetLevel()
            player.resetSex()
        }

        // TODO: don't refresh all items
        playerAdapter.notifyDataSetChanged()
    }

}
