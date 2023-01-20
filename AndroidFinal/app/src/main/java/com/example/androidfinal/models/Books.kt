package com.example.androidfinal.models

data class Books(
    val status: String,
    val totalResults: Int,
    val books: List<Book>
)
