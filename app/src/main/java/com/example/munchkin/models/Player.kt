package com.example.munchkin.models

class Player {

    companion object {
        private const val MALE: Boolean = false
        private const val FEMALE: Boolean = false
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

    fun changeSex() {
        sex = !sex
    }

}