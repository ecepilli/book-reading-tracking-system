package com.example.reading_tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel (val dao: BookDao) : ViewModel() {
    var newBookName = ""
    var newAuthor = ""
    val books = dao.getAll()
    private val _navigateToBook = MutableLiveData<Long?>()
    val navigateToBook: LiveData<Long?>
        get() = _navigateToBook

    fun onBookClicked(bookId: Long) {
        _navigateToBook.value = bookId
    }
    fun onBookNavigated() {
        _navigateToBook.value = null
    }
}