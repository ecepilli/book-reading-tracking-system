package com.example.reading_tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UpdateViewModel (id: Long, val dao: UserLibraryDao) : ViewModel() {
    val userLibrary = dao.get(id)
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList
    fun updateBook() {
        viewModelScope.launch {
            dao.update(userLibrary.value!!)
            _navigateToList.value = true
        }
    }
    fun deleteBook() {
        viewModelScope.launch {
            dao.delete(userLibrary.value!!)
            _navigateToList.value = true
        }
    }
    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}