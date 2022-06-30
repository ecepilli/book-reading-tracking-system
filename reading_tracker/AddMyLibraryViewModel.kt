package com.example.reading_tracker

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class AddMyLibraryViewModel (bookId: Long,val dao: BookDao,val userLibrary:UserLibraryDao) : ViewModel() {
    val book = dao.get(bookId)
    var newStartingDay = ""
    var newFinishingDay = ""
    var newComment = ""

    private val _newBookName = MutableLiveData<String>("")
    val newBookName: LiveData<String>
        get() = _newBookName
    private val _newBookAuthor = MutableLiveData<String>("")
    val newBookAuthor: LiveData<String>
        get() = _newBookAuthor
    private val _newPhoto = MutableLiveData<String>("")
    val newPhoto: LiveData<String>
        get() = _newPhoto
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun addBook() {
        viewModelScope.launch {
            val libraryBooks = Userlibrary()
            _newBookName.value=book.value?.bookName
            _newBookAuthor.value=book.value?.author
            _newPhoto.value=book.value?.photo_book
            libraryBooks.libraryBookName= newBookName.value.toString()
            libraryBooks.libraryBookAuthor= newBookAuthor.value.toString()
            libraryBooks.libraryBookPhoto= newPhoto.value.toString()
            libraryBooks.startingDate=newStartingDay
            libraryBooks.finishingDate=newFinishingDay
            libraryBooks.bookComment=newComment
            userLibrary.insert(libraryBooks)
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}