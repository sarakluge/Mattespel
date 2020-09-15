package com.example.positionssystemetspel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    lateinit var p1TextView: TextView
    lateinit var p2TextView: TextView
    lateinit var instTextView: TextView
    lateinit var cardView: TextView
    lateinit var cardButton: Button
    lateinit var hundredButton: Button
    lateinit var tenButton: Button
    lateinit var singleButton: Button
    lateinit var hundredText1: TextView
    lateinit var hundredText2: TextView
    lateinit var tenText1: TextView
    lateinit var tenText2: TextView
    lateinit var singleText1: TextView
    lateinit var singleText2: TextView
    var name1: String? = ""
    var name2: String? = ""
    var currentPlayer = 1
    var counter = 0
    var random = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        p1TextView = findViewById(R.id.textViewP1)
        p2TextView = findViewById(R.id.textViewP2)
        name1 = intent.getStringExtra("player1Name")
        name2 = intent.getStringExtra("player2Name")
        p1TextView.text = name1
        p2TextView.text = name2
        cardView = findViewById(R.id.numTextView)
        cardButton = findViewById<Button>(R.id.cardButton)
        instTextView = findViewById(R.id.instruktionTextView)
        hundredButton = findViewById(R.id.hundredButton)
        tenButton = findViewById(R.id.tenButton)
        singleButton = findViewById(R.id.singleButton)
        hundredText1 = findViewById(R.id.hundredTextView1)
        hundredText2 = findViewById(R.id.hundredTextView2)
        tenText1 = findViewById(R.id.tenTextView1)
        tenText2 = findViewById(R.id.tenTextView2)
        singleText1 = findViewById(R.id.singleTextView)
        singleText2 = findViewById(R.id.singleTextView2)

    }


    fun cardButtonPressed (view: View) {
        random = (0..9).random()
        cardView.text = random.toString()
        instTextView.text = "Välj vilken plats siffran ska ha"

        hundredButton.visibility = View.VISIBLE
        tenButton.visibility = View.VISIBLE
        singleButton.visibility = View.VISIBLE

    }

    fun hundredButton (view: View) {
        if(currentPlayer == 1) {
            hundredText1.text = random.toString()
            instTextView.text = "$name2's tur, ta ett kort"
            currentPlayer = 2
        } else if(currentPlayer == 2){
            hundredText2.text = random.toString()
            instTextView.text = "$name1's tur, ta ett kort"
            currentPlayer = 1
        }
        hundredButton.visibility = View.INVISIBLE
        tenButton.visibility = View.INVISIBLE
        singleButton.visibility = View.INVISIBLE
    }

    fun tenButton (view: View) {
        if(currentPlayer == 1) {
            tenText1.text = random.toString()
            currentPlayer = 2
            instTextView.text = "$name2's tur, ta ett kort"

        } else {
            tenText2.text = random.toString()
            currentPlayer = 1
            instTextView.text = "$name1's tur, ta ett kort"
        }
        hundredButton.visibility = View.INVISIBLE
        tenButton.visibility = View.INVISIBLE
        singleButton.visibility = View.INVISIBLE
    }

    fun singleButton (view: View) {
        if(currentPlayer == 1) {
            singleText1.text = random.toString()
            instTextView.text = "$name2's tur, ta ett kort"
            currentPlayer = 2
        } else {
            singleText2.text = random.toString()
            currentPlayer = 1
            instTextView.text = "$name1's tur, ta ett kort"
        }
        hundredButton.visibility = View.INVISIBLE
        tenButton.visibility = View.INVISIBLE
        singleButton.visibility = View.INVISIBLE
    }
}