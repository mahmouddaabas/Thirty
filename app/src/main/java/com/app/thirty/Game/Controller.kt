package com.app.thirty.Game

import Score
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
    private var values = IntArray(6);
    private var rerollAmount: Int = 0
    private var currentRound: Int = 1
    private var score: Score = Score()

    /**
     * Constructor for the class.
     */
    init {
        //Create dice for the game.
        createGameDice();
        //Set the local mainActivity object to the one sent in the class param.
        this.mainActivity = mainActivity;
    }

    /**
     * Creates 6 dice objects and stores them in an array.
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
            //Store the rolled value in the values array.
            values[currentDice] = value;
            drawImage(value, currentDice)
            currentDice++
        }
        for (element in values) {
            println(element)
        }
    }

    /**
     * Draws an image in the View depending on the value and dice rolled.
     */
    private fun drawImage(value: Int, diceNumber: Int){
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
        mainActivity.getDiceImageArray()[diceNumber].setImageDrawable(drawable)
    }

    /**
     * Rerolls an already thrown dice then stores the new value on its index in the array.
     */
    fun rerollDice(diceNumber: Int){
        if(rerollAmount < 2){
            var value = diceArray[diceNumber].roll();
            values[diceNumber] = value;
            drawImage(value, diceNumber)
            rerollAmount++;
        }
        else {
            mainActivity.showAlertDialog(mainActivity, "Alert","You have already rerolled your dice two times this round!");
        }
    }

    /**
     * Resets games values after finishing a round.
     */
    fun resetGame(){
        values = IntArray(6);
        rerollAmount = 0;
        currentDice = 0;
        diceArray = Array(6) { Dice() }
        val diceImageArray = mainActivity.getDiceImageArray()
        for (i in diceImageArray.indices) {
            diceImageArray[i].setImageDrawable(null)
        }
    }

    /**
     * Proceeds to the next round.
     */
    fun nextRound(){
        //All values must be filled before user can go to next round.
        if(values[5] != 0){
            resetGame();
            currentRound++;
            mainActivity.setRoundText("Round: $currentRound")
        }
        else {
            mainActivity.showAlertDialog(mainActivity, "Alert","You need to throw all your dice before you can proceed to the next round!");
        }
    }

    /**
     * Calculates the score for the thrown dice then proceeds to next round.
     */
    fun calculateScore(scoreType: Any){
        if(scoreType != "Pick Score") {
            //Create another class to calculate the score. Call method here.
            //All the dice values are stored in the values array.
            values[0] = 1
            values[1] = 1
            values[2] = 1
            values[3] = 2
            values[4] = 4
            values[5] = 4
            val sumScore = score.calculateCombinations(values, scoreType as String)
            println(sumScore)
            nextRound()
        }
    }
}