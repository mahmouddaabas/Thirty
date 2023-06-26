package se.umu.mada0474.thirty.Game

import kotlin.random.Random

/**
 * Represents a dice.
 * @author Mahmoud Daabas
 */
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