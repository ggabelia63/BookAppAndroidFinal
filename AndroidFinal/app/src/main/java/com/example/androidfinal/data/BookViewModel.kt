package com.example.androidfinal.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<BookDto>>
    private val repository: BookRepository

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        readAllData = repository.readAllData
    }

    fun addBook(bookDto: BookDto)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(bookDto)
        }
    }

    fun deleteBook(bookDto: BookDto)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(bookDto)
        }
    }
}