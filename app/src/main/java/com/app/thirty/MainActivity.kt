package com.app.thirty

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.app.thirty.Game.Controller

class MainActivity : AppCompatActivity() {
    private lateinit var controller: Controller
    private lateinit var diceImageArray: Array<ImageView>
    private val scoreOptions = arrayOf(
        "Pick Score", "Low", "4", "5", "6", "7", "8", "9", "10", "11", "12", "---"
    )
    private val rerollDiceOptions = arrayOf(
        "1", "2", "3", "4", "5", "6"
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

        // Fill the spinner with the scoreOptions array.
        val spinner = findViewById<Spinner>(R.id.scoreSpinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, scoreOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Fill the spinner with the rerollDiceOptions array.
        val rerollSpinner = findViewById<Spinner>(R.id.rerollSpinner)
        val rerollAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rerollDiceOptions)
        rerollAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rerollSpinner.adapter = rerollAdapter
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
     * Rerolls an already thrown dice.
     */
    fun rerollDice(view: View){
        val spinner = findViewById<Spinner>(R.id.rerollSpinner)
        val selectedItem = spinner.selectedItem.toString().toInt()-1; //-1 to account for starting at index 0.
        controller.rerollDice(selectedItem)
    }

    /**
     * Changes the text in the round textView.
     */
    fun setRoundText(text: String){
        val textView = findViewById<TextView>(R.id.roundText)
        textView.text = text;
    }

    /**
     * Returns the diceImageArray.
     */
    fun getDiceImageArray(): Array<ImageView> {
        return diceImageArray
    }

    /**
     * This function shows alert boxes.
     */
    fun showAlertDialog(context: Context, title: String, message: String) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                // Handle OK button click event here
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
    }
}