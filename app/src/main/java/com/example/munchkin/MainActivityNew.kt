package com.example.munchkin

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.munchkin.custom_views.PlayerView

class MainActivityNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = PlayerView(this, null)

        setContentView(view)
    }

}
