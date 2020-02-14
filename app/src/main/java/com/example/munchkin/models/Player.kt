package com.example.munchkin.models

class Player {

    private val TAG: String = Player::class.java.simpleName

    companion object {
        private const val MALE: Boolean = false
        private const val FEMALE: Boolean = true
    }

    var name: String = ""
    var sex: Boolean = MALE
    var level: Int = 1

    fun isMale() = sex == MALE
    fun isFemale() = sex == FEMALE

    fun levelUp() {
        if (level < 10) {
            ++level
        }
    }

    fun levelDown() {
        if (level > 1) {
            --level
        }
    }

    fun changeSex() { sex = !sex }

    fun resetLevel() { level = 1 }
    fun resetSex() { sex = MALE }

}