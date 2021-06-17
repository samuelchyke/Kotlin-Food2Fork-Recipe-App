package com.example.kotlinfood2forkrecipeapp.presentation.navigation

sealed class Screen (
    val route: String,
        ){

    object RecipeList: Screen("recipeList")

    object RecipeDetail: Screen("recipeDetail")
}