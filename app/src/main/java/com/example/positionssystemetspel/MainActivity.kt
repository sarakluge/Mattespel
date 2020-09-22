package com.example.positionssystemetspel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var player1: EditText
    lateinit var player2: EditText
    lateinit var startButton: Button
    lateinit var onePlayerButton: Button
    lateinit var twoPlayerButton: Button
    lateinit var textView: TextView
    var players = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)
        onePlayerButton = findViewById(R.id.onePlayerButton)
        twoPlayerButton = findViewById(R.id.twoPlayerButton)
        player1 = findViewById(R.id.player1Text)
        player2 = findViewById(R.id.player2Text)
        textView = findViewById(R.id.textView)

        startButton.setOnClickListener {
            startGameActivity()
            Log.d("!!!", "knappen trycktes")
        }
    }

    fun onePlayer(view: View) {
        player2.visibility = View.VISIBLE
        startButton.visibility = View.VISIBLE
        onePlayerButton.visibility = View.INVISIBLE
        twoPlayerButton.visibility = View.INVISIBLE
        textView.visibility = View.INVISIBLE
        players = 1

    }

    fun twoPlayer(view: View) {
        player1.visibility = View.VISIBLE
        player2.visibility = View.VISIBLE
        startButton.visibility = View.VISIBLE
        onePlayerButton.visibility = View.INVISIBLE
        twoPlayerButton.visibility = View.INVISIBLE
        textView.visibility = View.INVISIBLE
        players = 2
    }

    fun startGameActivity() {
        val intent = Intent(this, GameActivity::class.java)

        intent.putExtra("players", players)
        intent.putExtra("player1Name", player1.text.toString())
        intent.putExtra("player2Name", player2.text.toString())
        startActivity(intent)
    }

}