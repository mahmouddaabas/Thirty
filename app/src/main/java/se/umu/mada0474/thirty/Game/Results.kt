package se.umu.mada0474.thirty.Game

/**
 * This class holds the results of each round.
 * @author Mahmoud Daabas
 */
class Results {
    private val list: ArrayList<String> = ArrayList();
    private var totalScore: Int = 0

    /**
     * Saves a result from a round to the list.
     * Adds the recieved score to the total score variable.
     * @param score the acquired score.
     * @param currentRound the round where the result was achieved.
     * @param scoreType the score type the user used.
     */
    fun saveResult(score: Int, currentRound: Int, scoreType: String){
        list.add("Round: $currentRound - Score: $score - Score Type: $scoreType")
        totalScore += score
    }

    /**
     * Prints all the saved results.
     */
    fun printSavedResults(){
        println(list);
    }

    /**
     * Returns the list with the saved results.
     */
    fun getResultsList(): ArrayList<String> {
        return list
    }

    /**
     * Returns the total score from all rounds.
     */
    fun getTotalScore(): Int {
        return totalScore;
    }

}