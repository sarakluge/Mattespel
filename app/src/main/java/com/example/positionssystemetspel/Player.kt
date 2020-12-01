package com.example.positionssystemetspel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(@PrimaryKey(autoGenerate = true) var id: Int,
                  @ColumnInfo(name = "name") var name: String,
                  @ColumnInfo(name = "score") var score: Int)
