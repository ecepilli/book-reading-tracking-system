package com.example.reading_tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddMyLibraryViewModelFactory (private val bookId: Long,
                                    private val dao: BookDao,private val userLibrary:UserLibraryDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMyLibraryViewModel::class.java)) {
            return AddMyLibraryViewModel(bookId, dao, userLibrary ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}