package com.example.reading_tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateViewModelFactory (private val id: Long,
                              private val dao: UserLibraryDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            return UpdateViewModel(id, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}