package com.example.reading_tracker

import androidx.room.*
import java.util.*

@Entity(
    tableName = "user_library"
)
data class Userlibrary(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var userLibraryId: Long = 0,
    @ColumnInfo(name = "book_name") var libraryBookName: String="",
    @ColumnInfo(name = "book_author") var libraryBookAuthor: String="",
    @ColumnInfo(name = "book_photo") var libraryBookPhoto: String="",
    @ColumnInfo(name = "starting_date") var startingDate: String="",
    @ColumnInfo(name = "finishing_date") var finishingDate: String="",
    @ColumnInfo(name = "book_comment") var bookComment: String=""
)


