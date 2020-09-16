package com.example.positionssystemetspel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}