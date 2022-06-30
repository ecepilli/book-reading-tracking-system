package com.example.reading_tracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Users::class,Books::class,Userlibrary::class], version = 1, exportSchema = false)
abstract  class NewLibraryDatabase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val bookDao: BookDao
    abstract val libraryDao: UserLibraryDao

    companion object {
        @Volatile
        private var INSTANCE: NewLibraryDatabase? = null
        fun getInstance(context: Context): NewLibraryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewLibraryDatabase::class.java,
                        "reading_tracker_new_finally_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}