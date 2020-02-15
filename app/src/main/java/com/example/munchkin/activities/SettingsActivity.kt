package com.example.munchkin.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.example.munchkin.R
import com.example.munchkin.fragment.SettingsFragment


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(findViewById(R.id.action_bar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings_container, SettingsFragment())
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
        }

        return super.onOptionsItemSelected(item)
    }

}