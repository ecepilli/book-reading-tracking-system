package com.example.reading_tracker

import android.graphics.Bitmap
import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Books(
    @PrimaryKey(autoGenerate = true)
    var bookId: Long = 0L,
    @ColumnInfo(name = "book_name")
    var bookName: String = "",
    @ColumnInfo(name = "author")
    var author: String = "",
    @ColumnInfo(name = "photo_book")
    val photo_book:String
)