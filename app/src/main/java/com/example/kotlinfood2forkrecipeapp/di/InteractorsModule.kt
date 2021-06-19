package com.example.kotlinfood2forkrecipeapp.di

import com.example.kotlinfood2forkrecipeapp.cache.RecipeDao
import com.example.kotlinfood2forkrecipeapp.cache.model.RecipeEntityMapper
import com.example.kotlinfood2forkrecipeapp.interactors.recipe_list.SearchRecipes
import com.example.kotlinfood2forkrecipeapp.network.RecipeService
import com.example.kotlinfood2forkrecipeapp.network.model.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn( ViewModelComponent::class)
object InteractorsModule{

    @ViewModelScoped
    @Provides
    fun provideSearchRecipe(
        recipeService: RecipeService,
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
        recipeDtoMapper: RecipeDtoMapper,
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            recipeDao = recipeDao,
            entityMapper = recipeEntityMapper,
            recipeDtoMapper = recipeDtoMapper,
        )
    }
}
