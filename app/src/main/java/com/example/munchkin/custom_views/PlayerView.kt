package com.example.munchkin.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.munchkin.R

class PlayerView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.player_view,this)
    }

}