package com.example.reading_tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyLibraryViewModel(val dao: UserLibraryDao) : ViewModel() {
    val myBooks = dao.getAll()
    private val _navigateToBook = MutableLiveData<Long?>()
    val navigateToBook: LiveData<Long?>
        get() = _navigateToBook
    fun addBook() {
        viewModelScope.launch {

        }

    }
    fun onBookClicked(userLibraryId: Long) {
        _navigateToBook.value = userLibraryId
    }
    fun onBookNavigated() {
        _navigateToBook.value = null
    }
}