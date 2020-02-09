package com.example.munchkin.custom_views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.munchkin.R
import com.example.munchkin.models.Player

class PlayerView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val TAG: String = PlayerView::class.java.simpleName

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

            setName()
            setSex()
        }

        setLevel()

        name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                player.name = name.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        sex.setOnClickListener {
            player.changeSex()
            setSex()
        }

        levelUp.setOnClickListener {
            player.levelUp()
            setLevel()

            if (player.level == 10) {
                Toast.makeText(context, "${player.name} won", Toast.LENGTH_LONG).show()
            }
        }

        levelDown.setOnClickListener {
            player.levelDown()
            setLevel()
        }
    }

    private fun setName() {
        name.text = player.name
    }

    private fun setSex() {
        if (player.isMale()) {
            sex.setImageResource(R.drawable.ic_male)
            sex.setBackgroundResource(R.color.male)
        } else {
            sex.setImageResource(R.drawable.ic_female)
            sex.setBackgroundResource(R.color.female)
        }
    }

    private fun setLevel() {
        level.text = player.level.toString()
    }

}