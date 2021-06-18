package com.example.kotlinfood2forkrecipeapp.cache

import androidx.room.Dao
import androidx.room.Insert
import com.example.kotlinfood2forkrecipeapp.cache.model.RecipeEntity

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity): Long


}