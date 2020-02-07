package com.example.munchkin

import android.app.Activity
import android.os.Bundle
import com.example.munchkin.custom_views.PlayerView

class MainActivityNew : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(PlayerView(this, null))
    }

}
