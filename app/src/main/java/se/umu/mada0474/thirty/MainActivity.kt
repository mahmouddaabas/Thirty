package se.umu.mada0474.thirty

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import se.umu.mada0474.thirty.Game.Controller

/**
 * This is the main view of the program.
 * The game is played on this view.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var controller: Controller
    private lateinit var diceImageArray: Array<ImageView>
    private var scoreOptions = arrayOf(
        "Pick Score", "Low", "4", "5", "6", "7", "8", "9", "10", "11", "12",
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
        fillSpinners()
    }

    private fun fillSpinners(){
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
     * Switches the application to the ResultActivity when the game ends.
     */
    fun switchToResultActivity(results: ArrayList<String>, totalScore: Int){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("resultsArray", results)
        intent.putExtra("totalScore", totalScore)
        startActivity(intent)
    }

    /**
     * Removes a value from the score options.
     * This prevents the user from using the same score type twice.
     */
    fun removeValueFromScoreOptions(valueToRemove: String){
        val scoreOptionsList = scoreOptions.toMutableList()
        scoreOptionsList.remove(valueToRemove)
        val updatedScoreOptions = scoreOptionsList.toTypedArray()
        scoreOptions = updatedScoreOptions
        fillSpinners()
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

    /**
     * Saves the state of the application.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("roundText", findViewById<TextView>(R.id.roundText).text.toString())
        outState.putIntArray("diceValueArray", controller.getDiceValues())
        outState.putIntArray("rerolledDiceArray", controller.getRerolledDice())
    }

    /**
     * Restores the state of the application.
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val roundText = savedInstanceState.getString("roundText")
        findViewById<TextView>(R.id.roundText).text = roundText

        val rerolledDice = savedInstanceState.getIntArray("rerolledDiceArray")
        if (rerolledDice != null) {
            controller.setRerolledDice(rerolledDice)
        }

        val diceValues = savedInstanceState.getIntArray("diceValueArray")
        if (diceValues != null) {
            for (i in diceValues.indices) {
                controller.drawImage(diceValues[i], i)
            }
            //Pass the saved dice values back to the controller.
            controller.setDiceValues(diceValues)
        }
    }
}