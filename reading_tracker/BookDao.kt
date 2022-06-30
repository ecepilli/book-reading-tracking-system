package com.example.reading_tracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books: Books)
    @Update
    suspend fun update(books: Books)
    @Delete
    suspend fun delete(books: Books)
    @Query("SELECT * FROM book_table WHERE bookId = :key")
    fun get(key: Long): LiveData<Books>
    @Query("SELECT * FROM book_table ORDER BY bookId DESC")
    fun getAll(): LiveData<List<Books>>
    @Query("SELECT * FROM book_table WHERE book_name LIKE :searchQuery")
    suspend fun getSearch(searchQuery:String):Books
}