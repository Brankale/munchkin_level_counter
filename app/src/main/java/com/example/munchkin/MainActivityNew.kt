package com.example.munchkin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivityNew : AppCompatActivity() {

    private val TAG: String = MainActivityNew::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        setSupportActionBar(findViewById(R.id.action_bar))
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
            newGame()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun addPlayer() {
        TODO("implement function add player")
    }

    private fun removePlayer() {
        TODO("implement function remove player")
    }

    private fun newGame() {
        TODO("implement function new game")
    }

}
