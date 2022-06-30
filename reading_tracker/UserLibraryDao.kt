package com.example.reading_tracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserLibraryDao {
    @Insert
    suspend fun insert(userlibrary: Userlibrary)
    @Update
    suspend fun update(userlibrary: Userlibrary)
    @Delete
    suspend fun delete(userlibrary: Userlibrary)
    @Query("SELECT * FROM user_library WHERE id = :key")
    fun get(key: Long): LiveData<Userlibrary>
    @Query("SELECT * FROM user_library ORDER BY id DESC")
    fun getAll(): LiveData<List<Userlibrary>>
}