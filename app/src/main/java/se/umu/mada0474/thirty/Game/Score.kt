/**
 * This class handles everything score related.
 * @author Mahmoud Daabas
 */

class Score {

    /**
     * Calculates the possible pairs of dice rolls and their sums based on the given values array and score type.
     *
     * @param values    An integer array representing the dice rolls.
     * @param scoreType A string representing the score type ("Low" or a value from 4 to 12).
     * @return The sum of the possible pairs based on the score type.
     */

    fun calculateCombinations(values: IntArray, scoreType: String): Int {
        var returnValue = 0

        val unsortedValues = mutableListOf<Int>()

        val wantedSum = when (scoreType) {
            "Low" -> 3 // Minimum sum for LOW score type
            else -> scoreType.toInt() // Sum based on the score type
        }

        // Remove values larger than the wanted sum
        for (i in values.indices) {
            if (values[i] <= wantedSum)
                unsortedValues.add(values[i])
        }

        // Sort values in descending order
        val sortedValues = unsortedValues.sortedDescending().toMutableList()

        // Algorithm for the special case of LOW score type
        if (scoreType == "Low") {
            var sum = 0
            for (i in 0 until sortedValues.size) {
                val value = sortedValues[i]
                if (value <= 3) {
                    sum += value
                }
            }
            return sum
        }

        // Calls countSortedValues a maximum of six times
        for (i in 0 until sortedValues.size) {
            val returnPair = countSortedValues(sortedValues, wantedSum)
            // One successful combination has been found
            if (returnPair.second == 1) {
                returnValue += wantedSum
            }
            // No successful combination found
            if (returnPair.first.isNotEmpty()) {
                sortedValues.clear()
                sortedValues.addAll(returnPair.first)
            } else {
                // All combinations tested
                break
            }
        }
        return returnValue
    }


    /**
     * Helper function that counts the sorted values to find possible pairs of dice rolls.
     *
     * @param values     A mutable list of integers representing the sorted dice rolls.
     * @param wantedSum  The desired sum of the pairs.
     * @return A pair containing the remaining values after successful combinations and a flag indicating if a successful combination was found.
     */

    private fun countSortedValues(values: MutableList<Int>, wantedSum: Int): Pair<MutableList<Int>, Int> {
        val sortedValues = values.toMutableList()
        val sum = sortedValues[0]
        sortedValues.removeAt(0)
        val unsuccessfulReturnValues = sortedValues.toMutableList()

        var remainingSum = wantedSum - sum

        for (i in sortedValues.indices) {
            val temp = sortedValues[i]
            if (temp <= remainingSum && temp != 0) {
                remainingSum -= temp
                sortedValues[i] = 0
            }
            if (remainingSum == 0) {
                break
            }
        }
        sortedValues.removeAll { it == 0 }

        return if (remainingSum == 0) {
            Pair(sortedValues, 1)
        } else {
            Pair(unsuccessfulReturnValues, 0)
        }
    }

}
