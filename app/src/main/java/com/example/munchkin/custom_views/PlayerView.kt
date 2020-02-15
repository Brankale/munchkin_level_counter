package com.example.munchkin.custom_views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.example.munchkin.R
import com.example.munchkin.models.Player

class PlayerView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    private var player: Player

    private val name: TextView
    private val sex: ImageView
    private val level: TextView
    private val levelUp: FrameLayout
    private val levelDown: FrameLayout

    init {
        View.inflate(context, R.layout.player_view,this)

        name = findViewById(R.id.name)
        sex = findViewById(R.id.sex)
        level = findViewById(R.id.current_level)
        levelUp = findViewById(R.id.level_up)
        levelDown = findViewById(R.id.level_down)

        player = Player()

        // TODO: xml layout params are lost in the inflate phase so they must be set
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PlayerView, 0, 0)

            player.name = typedArray.getString(R.styleable.PlayerView_name) ?: ""
            player.level = typedArray.getInt(R.styleable.PlayerView_level, 1)
            player.sex = typedArray.getInt(R.styleable.PlayerView_sex, 0) > 0

            typedArray.recycle()

            updateName()
            updateSex()
        }

        updateLevel()

        name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                player.name = name.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        sex.setOnClickListener {
            player.changeSex()
            updateSex()
        }

        levelUp.setOnClickListener {
            player.levelUp()
            updateLevel()

            if (player.level == 10) {
                // TODO: substitute with /values/stings key
                if (player.name.isNotEmpty()) {
                    Toast.makeText(context, resources.getString(R.string.player_wins, player.name), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, R.string.someone_won, Toast.LENGTH_LONG).show()
                }
            }
        }

        levelDown.setOnClickListener {
            player.levelDown()
            updateLevel()
        }
    }

    fun bind(player: Player) {
        this.player = player
        updateName()
        updateLevel()
        updateSex()
    }

    private fun updateName() {
        name.text = player.name
    }

    private fun updateSex() {
        if (player.isMale()) {
            sex.setImageResource(R.drawable.ic_male)
            sex.setBackgroundResource(R.color.male)
        } else {
            sex.setImageResource(R.drawable.ic_female)
            sex.setBackgroundResource(R.color.female)
        }
    }

    private fun updateLevel() {
        level.text = player.level.toString()
    }

}