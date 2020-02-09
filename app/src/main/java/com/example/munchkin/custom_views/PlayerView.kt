package com.example.munchkin.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.munchkin.Player
import com.example.munchkin.R

class PlayerView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val player: Player

    private val name: TextView
    private val sex: ImageView
    private val level: TextView
    private val levelUp: ImageView
    private val levelDown: ImageView

    init {
        View.inflate(context, R.layout.player_view,this)

        name = findViewById(R.id.name)
        sex = findViewById(R.id.sex)
        level = findViewById(R.id.current_level)
        levelUp = findViewById(R.id.level_up)
        levelDown = findViewById(R.id.level_down)

        player = Player()

        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PlayerView, 0, 0)

            player.name = typedArray.getString(R.styleable.PlayerView_name) ?: ""
            player.level = typedArray.getInt(R.styleable.PlayerView_level, 1)
            player.sex = typedArray.getInt(R.styleable.PlayerView_sex, 0) > 0

            typedArray.recycle()
        }

        sex.setOnClickListener {
            player.changeSex()

            if (player.isMale()) {
                sex.setImageResource(R.drawable.ic_male)
                sex.setBackgroundResource(R.color.male)
            } else {
                sex.setImageResource(R.drawable.ic_female)
                sex.setBackgroundResource(R.color.female)
            }
        }
    }

}