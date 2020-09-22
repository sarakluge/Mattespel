package com.example.positionssystemetspel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
    lateinit var cardBack: TextView
    lateinit var scoreView1: TextView
    lateinit var scoreView2: TextView

    var name1: String? = ""
    var name2: String? = ""
    var currentPlayer = 1
    var counter = 0
    var random = 0
    var numberP1 = 0
    var numberP2 = 0
    var score1 = 0
    var score2 = 0
    var players = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        p1TextView = findViewById(R.id.textViewP1)
        p2TextView = findViewById(R.id.textViewP2)
        cardView = findViewById(R.id.numTextView)
        cardButton = findViewById(R.id.cardButton)
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
        cardBack = findViewById(R.id.numTextViewBack)
        scoreView1 = findViewById(R.id.score1)
        scoreView2 = findViewById(R.id.score2)

        name1 = intent.getStringExtra("player1Name")
        name2 = intent.getStringExtra("player2Name")
        players = intent.getIntExtra("players", 1)
        instTextView.text = "$name1's tur, ta ett kort"

        if(players == 1){
            p1TextView.text = "Sally"
            p2TextView.text = name2
        } else {
            p1TextView.text = name1
            p2TextView.text = name2
        }
    }

    fun playAgain(view: View) {
        val endFragment = supportFragmentManager.findFragmentByTag("endFragment")

        if (endFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(endFragment)
            transaction.commit()
        }
        playAgainButton.visibility = View.INVISIBLE
        cardButton.visibility = View.VISIBLE
        hundredText1.text = null
        hundredText2.text = null
        tenText1.text = null
        tenText2.text = null
        singleText1.text = null
        singleText2.text = null
        getPoints()
        counter = 0

    }

    fun startEndFragment() {
        if(counter == 6){
            sumNumberP1()
            sumNumberP2()

            cardButton.visibility = View.INVISIBLE

            val endFragment = EndFragment.newInstance(getWinner())
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, endFragment, "endFragment")
            transaction.commit()
            playAgainButton.visibility = View.VISIBLE

        }
    }

    fun getPoints() {
        scoreView1.text = "Poäng: $score1"
        scoreView2.text = "Poäng: $score2"
    }

    fun getWinner(): String{
        var winner = ""
        if(sumNumberP1() == sumNumberP2()) {
            winner = "Det blev oavgjort"
            score1 ++
            score2 ++
        } else if(sumNumberP1() > sumNumberP2()) {
            winner = "$name1 vann denna omgång"
            score1 ++
        } else if(sumNumberP1() < sumNumberP2()) {
            winner = "$name2 vann denna omgång"
            score2 ++
        }
        return winner
    }

    fun sumNumberP2(): Int{
        val hundredInt2 = hundredText2.text.toString().toInt() * 100
        val tenInt2 = tenText2.text.toString().toInt() * 10
        val singleInt2 = hundredText2.text.toString().toInt()
        numberP2 = hundredInt2 + tenInt2 + singleInt2
        return numberP2
    }

    fun sumNumberP1(): Int{
        val hundredInt1 = hundredText1.text.toString().toInt() * 100
        val tenInt1 = tenText1.text.toString().toInt() * 10
        val singleInt1 = hundredText1.text.toString().toInt()
        numberP1 = hundredInt1 + tenInt1 + singleInt1
        return numberP1
    }


    fun cardButtonPressed (view: View) {
        counter ++
        random = (0..9).random()
        cardView.text = random.toString()
        instTextView.text = "Välj vilken plats siffran ska ha"

        hundredButton.visibility = View.VISIBLE
        tenButton.visibility = View.VISIBLE
        singleButton.visibility = View.VISIBLE

        if(currentPlayer == 1) {
            if(hundredText1.text != ""){
                hundredButton.visibility = View.INVISIBLE
            }
            if(tenText1.text != ""){
                tenButton.visibility = View.INVISIBLE
            }
            if(singleText1.text != ""){
                singleButton.visibility = View.INVISIBLE
            }

        } else if(currentPlayer == 2) {
            if (hundredText2.text != "") {
                hundredButton.visibility = View.INVISIBLE
            }
            if (tenText2.text != "") {
                tenButton.visibility = View.INVISIBLE
            }
            if (singleText2.text != "") {
                singleButton.visibility = View.INVISIBLE
            }
        }
        cardBack.visibility = View.INVISIBLE
        cardButton.visibility = View.INVISIBLE

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
        cardButton.visibility = View.VISIBLE
        cardBack.visibility = View.VISIBLE
        startEndFragment()
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
        cardButton.visibility = View.VISIBLE
        cardBack.visibility = View.VISIBLE
        startEndFragment()
    }

    fun singleButton(view: View) {
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
        cardButton.visibility = View.VISIBLE
        cardBack.visibility = View.VISIBLE
        startEndFragment()
    }
}