/**
 * This class handles the score calculating for the program.
 * @author Mahmoud Daabas
 */
class Score {

    /**
     * Determines which function to run based on the score type.
     *
     * @param scoreType The score type to determine the calculation logic.
     * @param dice The array of dice values.
     * @return The calculated score based on the score type and dice values.
     */
    fun calculateScore(scoreType: String, dice: IntArray): Int {
        var sum = 0;
        sum = if(scoreType == "Low"){
            scoreIsLow("3".toInt(), dice)
        } else {
            scoreNotLow(scoreType.toInt(), dice)
        }
        return sum
    }

    /**
     * Calculates the sum of dice combinations when the score type is not low.
     *
     * @param scoreType The score type to determine the calculation logic.
     * @param dice The array of dice values.
     * @return The sum of dice combinations when the score type is not low.
     */
    private fun scoreNotLow(scoreType: Int, dice: IntArray): Int{
        var sum = 0;
        val uniqueCombinations = mutableSetOf<List<Int>>()
        findDiceCombinations(dice, scoreType, mutableListOf(), 0, uniqueCombinations)

        uniqueCombinations.forEach { combination ->
            sum += combination.sum();
        }

        return sum
    }

    /**
     * Calculates the sum of values from the dice array when the score type is Low.
     *
     * @param scoreType The score type to determine the calculation logic.
     * @param dice The array of dice values.
     * @return The sum of values from the dice array when the score type is Low.
     */
    private fun scoreIsLow(scoreType: Int, dice: IntArray): Int{
        val sum = dice.filter { it <= scoreType }
        return sum.sum();
    }

    /**
     * Finds all unique combinations from the given array that add up to the target sum.
     *
     * @param array The input array of dice values.
     * @param targetSum The target sum to be achieved by combining the dice values.
     * @param currentCombination The current combination being built during recursion.
     * @param startIndex The index to start considering elements from the array.
     * @param uniqueCombinations The set to store the unique combinations found.
     */
    private fun findDiceCombinations(
        array: IntArray,
        targetSum: Int,
        currentCombination: MutableList<Int>,
        startIndex: Int,
        uniqueCombinations: MutableSet<List<Int>>
    ) {
        if (targetSum == 0) {
            uniqueCombinations.add(currentCombination.toList())
            return
        }

        if (targetSum < 0 || startIndex >= array.size) {
            return
        }

        for (i in startIndex until array.size) {
            currentCombination.add(array[i])
            findDiceCombinations(array, targetSum - array[i], currentCombination, i + 1, uniqueCombinations)
            currentCombination.removeAt(currentCombination.size - 1)
        }
    }
}

    /**
     * Function to test the score class.
     */
    fun main() {
        val s = Score()

        // TEST
        val values = intArrayOf(3, 4, 5, 6, 1, 6)
        val scoreType = "Low"
        val sum = s.calculateScore(scoreType, values)
        println(sum)
    }
