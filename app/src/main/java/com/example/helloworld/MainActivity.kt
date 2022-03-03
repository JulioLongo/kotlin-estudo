package com.example.helloworld

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var diceImage : ImageView
    lateinit var rollButton : Button
    lateinit var doneButton : Button
    lateinit var nextButton : Button
    lateinit var editText : EditText
    lateinit var nickname_text : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        /*window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        actionBar?.hide()*/
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // adicionar toolbar
        setSupportActionBar(findViewById(R.id.my_toolbar))

        diceImage = findViewById<ImageView>(R.id.dice_image)
        rollButton = findViewById<Button>(R.id.roll_button)
        doneButton= findViewById<Button>(R.id.done_button)
        nextButton= findViewById<Button>(R.id.next_button)
        editText = findViewById<EditText>(R.id.nickname_edit)
        nickname_text = findViewById<TextView>(R.id.nickname_text)


        nextButton.setOnClickListener { nextScreen() }
        rollButton.setOnClickListener { rollDice() }
        doneButton.setOnClickListener { addNickname(it) }
        nickname_text.setOnClickListener { updateNickname(it) }
    }

    private fun rollDice() {
        val snack = Snackbar.make(findViewById(android.R.id.content),"This is a simple Snackbar",Snackbar.LENGTH_LONG)
        snack.show()

        val randomInt = getRandomDiceImage()

        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)

        Toast.makeText(this, "button clicked",
            Toast.LENGTH_SHORT).show()

    }

    private fun getRandomDiceImage() : Int {
        return (1..6).random()
    }

    private fun addNickname(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        nicknameTextView.text = editText.text
        nicknameTextView.visibility = View.VISIBLE
        editText.visibility = View.GONE
        doneButton.visibility = View.GONE

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname (view: View) {
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        editText.requestFocus()
        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }

    private fun nextScreen () {
        val intent = Intent(this, ConstraintLayout::class.java).apply {
            putExtra(EXTRA_MESSAGE, "oii")
        }
        startActivity(intent)
    }


}