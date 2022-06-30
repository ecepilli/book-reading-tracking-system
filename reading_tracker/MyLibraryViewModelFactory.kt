package com.example.reading_tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyLibraryViewModelFactory (private val dao: UserLibraryDao)
    : ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyLibraryViewModel::class.java)) {
            return MyLibraryViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}