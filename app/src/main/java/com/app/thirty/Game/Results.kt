package com.app.thirty.Game

/**
 * This class holds the results of each round.
 * @author Mahmoud Daabas
 */
class Results {
    private val list: ArrayList<String> = ArrayList();

    /**
     * Saves a result from a round to the list.
     * @param score the acquired score.
     * @param currentRound the round where the result was achieved.
     * @param scoreType the score type the user used.
     */
    fun saveResult(score: Int, currentRound: Int, scoreType: String){
        list.add("Round: $currentRound\n Score: $score\nScore Type Used: $scoreType")
    }

    /**
     * Prints all the saved results.
     */
    fun printSavedResults(){
        println(list);
    }
}