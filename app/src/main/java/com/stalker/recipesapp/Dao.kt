package com.stalker.recipesapp

import androidx.room.Dao
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM recipe")
//    fun getAll(): ArrayList<Recipe_Modal?>?
    fun getAll(): List<Recipe_Modal>?
}
