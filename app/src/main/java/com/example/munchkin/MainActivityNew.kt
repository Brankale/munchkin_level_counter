package com.example.munchkin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.munchkin.custom_views.PlayerView

class MainActivityNew : AppCompatActivity() {

    private val TAG: String = MainActivityNew::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = PlayerView(this, null)

        setContentView(view)
    }

}
