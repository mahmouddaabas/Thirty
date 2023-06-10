package com.app.thirty.Game

import kotlin.random.Random

class Dice {

    private var value: Int = 0

    /**
     * Rolls the dice and returns a value.
     */
    fun roll(): Int {
        val randomValue = Random.nextInt(1, 7) // Rolls a number between 1 and 6
        value = randomValue
        return value
    }
}