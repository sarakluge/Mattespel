package com.example.positionssystemetspel

import android.content.Intent
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
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

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
    lateinit var db: AppDatabase
    var players = 0
    var playerList: PlayerList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = Job()

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

        db = AppDatabase.getInstance(this)

        loadAllPlayers()




    }

    fun setAdapter() {
        if(playerList != null) {
            recyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = HighScoreAdapter(this, playerList!!)
            recyclerView.adapter = adapter
        }
    }

    fun loadAllPlayers() {
        val player = async(Dispatchers.IO) {
            db.playerDao.getAll()
        }
        launch {
            val list = player.await().toMutableList()

            playerList = PlayerList(list)
            setAdapter()

            Log.d("!!!", "${list.size}")
        }

    }


    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
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
        highScoreTextView.visibility = View.INVISIBLE

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
        highScoreTextView.visibility = View.INVISIBLE

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