package com.example.androidfinal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class BookDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val source : String,
    val title: String,
    val description: String,
    val urlToImage: String,
)
