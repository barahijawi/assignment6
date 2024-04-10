package com.example.newapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Sport::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sportDao(): SportDao
}
