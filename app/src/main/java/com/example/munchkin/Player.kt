package com.example.munchkin

class Player {

    private val MALE: Boolean = false
    private val FEMALE: Boolean = true

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

    fun changeSex() {
        sex = !sex
    }

}