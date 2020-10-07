package com.example.positionssystemetspel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var highScoreTextView: TextView
    lateinit var textView: TextView
    lateinit var speechBubble: TextView
    lateinit var player1: EditText
    lateinit var player2: EditText
    lateinit var startButton: Button
    lateinit var onePlayerButton: Button
    lateinit var twoPlayerButton: Button
    lateinit var image: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var sharedPreferences: SharedPreferences

    var players = 0
    var playersHighScoreList = listOf<Player>(Player("Sara", 324),
        Player("Andreas", 326),
        Player("My", 898),
        Player("Ludwig", 499),
        Player("Moa", 543),
        Player("Hedvig", 752),
        Player("Ted", 524))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)
        onePlayerButton = findViewById(R.id.onePlayerButton)
        twoPlayerButton = findViewById(R.id.twoPlayerButton)
        player1 = findViewById(R.id.player1Text)
        player2 = findViewById(R.id.player2Text)
        textView = findViewById(R.id.textView)
        image = findViewById(R.id.squirrelImage)
        speechBubble = findViewById(R.id.speechBubble)
        recyclerView = findViewById(R.id.highScoreRecyclerView)
        highScoreTextView = findViewById(R.id.textViewhighScore)

        sharedPreferences = this.getSharedPreferences("sara", Context.MODE_PRIVATE)

        sharedPreferences.edit().putString("whiteboard", "sara").apply()

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = HighScoreAdapter(this, playersHighScoreList)
        recyclerView.adapter = adapter



        //startButton.setOnClickListener {
         //   startGameActivity()
         //   Log.d("!!!", "knappen trycktes")
       // }
    }

    override fun onRestart() {
        super.onRestart()
        textView.visibility = View.VISIBLE
        onePlayerButton.visibility = View.VISIBLE
        twoPlayerButton.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE
        highScoreTextView.visibility = View.VISIBLE
        player1.visibility = View.INVISIBLE
        player2.visibility = View.INVISIBLE
        startButton.visibility = View.INVISIBLE

        player1.text = null
        player2.text = null

        image.isClickable = true
    }


    fun showInstruktion(view: View) {

        if(speechBubble.visibility == View.INVISIBLE){
            speechBubble.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
            highScoreTextView.visibility = View.INVISIBLE
        } else {
            speechBubble.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
            highScoreTextView.visibility = View.VISIBLE
        }
    }

    fun onePlayer(view: View) {
        players = 1

        player2.visibility = View.VISIBLE
        startButton.visibility = View.VISIBLE
        onePlayerButton.visibility = View.INVISIBLE
        twoPlayerButton.visibility = View.INVISIBLE
        textView.visibility = View.INVISIBLE
        speechBubble.visibility = View.INVISIBLE
        recyclerView.visibility = View.INVISIBLE

        image.isClickable = false
    }

    fun twoPlayer(view: View) {
        players = 2

        player1.visibility = View.VISIBLE
        player2.visibility = View.VISIBLE
        startButton.visibility = View.VISIBLE
        onePlayerButton.visibility = View.INVISIBLE
        twoPlayerButton.visibility = View.INVISIBLE
        textView.visibility = View.INVISIBLE
        speechBubble.visibility = View.INVISIBLE
        recyclerView.visibility = View.INVISIBLE

        image.isClickable = false
    }

    fun startGameActivity(view: View) {
        val intent = Intent(this, GameActivity::class.java)

        intent.putExtra("players", players)
        intent.putExtra("player1Name", player1.text.toString())
        intent.putExtra("player2Name", player2.text.toString())
        startActivity(intent)
    }
}