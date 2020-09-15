package com.example.positionssystemetspel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText



class MainActivity : AppCompatActivity() {

    lateinit var player1: EditText
    lateinit var player2: EditText
    lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)

        startButton.setOnClickListener {
            startGameActivity()
            Log.d("!!!", "knappen trycktes")
        }
    }

    fun startGameActivity() {
        player1 = findViewById(R.id.player1Text)
        player2 = findViewById(R.id.player2Text)

        var intent = Intent(this, GameActivity::class.java)

        intent.putExtra("player1Name", player1.text.toString())
        intent.putExtra("player2Name", player2.text.toString())
        startActivity(intent)
    }
}