package com.example.munchkin.models

class Player {

    companion object {
        private const val MALE: Boolean = false
    }

    private var minLevel: Int = 1
    var maxLevel: Int = 10

    var name: String = ""
    var sex: Boolean = MALE
    var level: Int = 1

    fun isMale() = sex == MALE

    fun levelUp() {
        if (level < maxLevel) {
            ++level
        }
    }

    fun levelDown() {
        if (level > minLevel) {
            --level
        }
    }

    fun isAtMaxLevel() = level == maxLevel

    fun changeSex() { sex = !sex }

    fun resetLevel() { level = 1 }
    fun resetSex() { sex = MALE }

}