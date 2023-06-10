package com.app.thirty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.app.thirty.Game.Controller

class MainActivity : AppCompatActivity() {
    private lateinit var controller: Controller
    private lateinit var diceImageArray: Array<ImageView>
    private val scoreOptions = arrayOf(
        "Pick Score", "Low", "4", "5", "6", "7", "8", "9", "10", "11", "12", "---"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create controller object.
        controller = Controller(this);

        //Add dice ImageViews to the array.
        diceImageArray = Array(6) { index ->
            val resourceId = resources.getIdentifier("diceImage${index + 1}", "id", packageName)
            findViewById<ImageView>(resourceId)
        }

        // Fill the spinner with the options array.
        val spinner = findViewById<Spinner>(R.id.scoreSpinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, scoreOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    /**
     * Get the selected score by the user.
     */
    fun getSelectedScore(view: View){
        val spinner = findViewById<Spinner>(R.id.scoreSpinner)
        controller.calculateScore(spinner.selectedItem)
    }

    /**
     * Throws a dice.
     */
    fun throwDice(view: View) {
        controller.throwDice(view)
    }

    /**
     * Returns the diceImageArray.
     */
    fun getDiceImageArray(): Array<ImageView> {
        return diceImageArray
    }
}