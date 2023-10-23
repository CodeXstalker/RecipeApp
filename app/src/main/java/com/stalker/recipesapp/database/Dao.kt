package com.stalker.recipesapp.database

import androidx.room.Dao
import androidx.room.Query
import com.stalker.recipesapp.modal.Recipe_Modal

@Dao
interface Dao {
    @Query("SELECT * FROM recipe")
//    fun getAll(): ArrayList<Recipe_Modal?>?
    fun getAll(): List<Recipe_Modal>?
}
