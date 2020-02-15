package com.example.munchkin.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.example.munchkin.R
import com.example.munchkin.custom_views.PlayerAdapter
import com.example.munchkin.custom_views.PlayerListView
import com.example.munchkin.models.Player

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {
        private const val MIN_PLAYERS = 3
        private const val MAX_PLAYERS = 6
    }

    private lateinit var playerListView: PlayerListView
    private lateinit var playerAdapter: PlayerAdapter
    private val playerList = ArrayList<Player>(MIN_PLAYERS)
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.action_bar))

        playerListView = findViewById(R.id.player_list)
        playerAdapter = PlayerAdapter(playerList)
        playerListView.adapter = playerAdapter

        // add minimum number of players
        for (i in 0 until MIN_PLAYERS) {
            addPlayer()
        }

        PreferenceManager
                .getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        PreferenceManager
                .getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add_player -> {
            addPlayer()
            true
        }
        R.id.action_remove_player -> {
            removePlayer()
            true
        }
        R.id.action_new_game -> {

            AlertDialog.Builder(this)
                    .setMessage(R.string.dialog_new_game)
                    .setPositiveButton(android.R.string.yes) { _, _ -> newGame() }
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()

            true
        }
        R.id.action_settings -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        sharedPreferences?.let {
            if (it.getBoolean("keep_screen_on", false)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
        }
    }

    private fun addPlayer() {
        if (playerList.size < MAX_PLAYERS) {
            playerList.add(Player())
            playerAdapter.notifyItemInserted(playerList.lastIndex)
        } else {
            toast?.cancel()     // cancel previous toast
            toast = Toast.makeText(this, R.string.max_players, Toast.LENGTH_SHORT)
            toast?.show()
        }
    }

    private fun removePlayer() {
        if (playerList.size > MIN_PLAYERS) {
            playerList.removeAt(playerList.lastIndex)
            playerAdapter.notifyItemRemoved(playerList.size)
        } else {
            toast?.cancel()     // cancel previous toast
            toast = Toast.makeText(this, R.string.min_players, Toast.LENGTH_SHORT)
            toast?.show()
        }
    }

    private fun newGame() {
        for (player in playerList) {
            player.resetLevel()
            player.resetSex()
        }

        playerAdapter.notifyDataSetChanged()
    }

}
