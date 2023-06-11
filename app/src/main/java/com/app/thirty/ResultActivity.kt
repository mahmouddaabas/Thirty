package com.app.thirty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

/**
 * This class is displayed at the end of the game.
 * It shows the player their results and different score types they used.
 */
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Get the data that was sent with the intent when swapping activity.
        val intent = intent;
        val resultsArray = intent.getStringArrayListExtra("resultsArray")
        setResultsText(ArrayList<String>(resultsArray ?: emptyList()))

        val totalScore = intent.getIntExtra("totalScore", 0)
        setTotalScoreText(totalScore)
    }

    /**
     * Adds the results to the text view.
     */
    private fun setResultsText(results: ArrayList<String>) {
        val textView = findViewById<TextView>(R.id.resultsText)
        for (result in results) {
            textView.append(result + "\n\n")
        }
    }

    /**
     * Sets the total score to the text view.
     */
    private fun setTotalScoreText(totalScore: Int){
        val textView = findViewById<TextView>(R.id.totalScoreText)
        textView.text = "Total Score: $totalScore"
    }
}