package com.app.thirty.Game

import android.view.View
import androidx.core.content.ContextCompat
import com.app.thirty.MainActivity
import com.app.thirty.R

/**
 * This class handles the game logic.
 * @author Mahmoud Daabas
 */
class Controller(mainActivity: MainActivity) {

    private lateinit var diceArray: Array<Dice>
    private var currentDice: Int = 0;
    private lateinit var mainActivity: MainActivity;

    /**
     * Constructor for the class.
     */
    init {
        createGameDice();
        //Set the local mainActivity object to the one sent in the class param.
        this.mainActivity = mainActivity;
    }

    /**
     * Creates 6 dice and stores them in a dice array.
     */
    private fun createGameDice(){
        diceArray = Array(6) { Dice() }
    }

    /**
     * Rolls a dice in the array then increments the currentDice counter.
     */
    fun throwDice(view: View){
        //Throw 2 dice at once.
        for (i in 1..2) {
            val value = diceArray[currentDice].roll()
            drawImage(value, currentDice)
            currentDice++
        }
    }

    /**
     * Draws an image in the View depending on the value and dice rolled.
     */
    private fun drawImage(value: Int, currentDice: Int){
        //Set the image that will be loaded in the View based on the value of the dice.
        val drawable = when (value) {
            1 -> ContextCompat.getDrawable(mainActivity, R.drawable.white1)
            2 -> ContextCompat.getDrawable(mainActivity, R.drawable.white2)
            3 -> ContextCompat.getDrawable(mainActivity, R.drawable.white3)
            4 -> ContextCompat.getDrawable(mainActivity, R.drawable.white4)
            5 -> ContextCompat.getDrawable(mainActivity, R.drawable.white5)
            6 -> ContextCompat.getDrawable(mainActivity, R.drawable.white6)
            else -> null
        }
        //Set the image to the current dice. (Increments by one after each roll).
        mainActivity.getDiceImageArray()[currentDice].setImageDrawable(drawable)
    }

    /**
     * Calculates the score depending on what the user selected.
     */
    fun calculateScore(score: Any){
        if(score != "Pick Score") {
            //Create another class to calculate the score. Call method here.
            println(score)
        }
    }
}