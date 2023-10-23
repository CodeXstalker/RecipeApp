package com.stalker.recipesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stalker.recipesapp.modal.Recipe_Modal

@Database(entities = [Recipe_Modal::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): Dao
}