package com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe

sealed class RecipeEvent{

    data class GetRecipeEvent(
        val id: Int
    ): RecipeEvent()

}