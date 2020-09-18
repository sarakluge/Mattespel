package com.example.positionssystemetspel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

lateinit var winnerTextView: TextView

class endActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        val winner = intent.getStringExtra("winner")

        winnerTextView = findViewById(R.id.winnerText)
        winnerTextView.text = winner

    }
    fun playAgain(view: View){

        val intent = Intent(this, GameActivity::class.java)

        startActivity(intent)
    }
}