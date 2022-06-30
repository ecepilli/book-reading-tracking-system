package com.example.reading_tracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insert(users: Users)
    @Update
    suspend fun update(users: Users)
    @Delete
    suspend fun delete(users: Users)
    @Query("SELECT * FROM user_table WHERE user_mail =:mail AND user_password=:password")
    suspend fun getUser(mail: String,password: String): Users
    @Query("SELECT * FROM user_table WHERE userId = :key")
    fun get(key: Long): LiveData<Users>
    @Query("SELECT * FROM user_table ORDER BY userId DESC")
    fun getAll(): LiveData<List<Users>>
}