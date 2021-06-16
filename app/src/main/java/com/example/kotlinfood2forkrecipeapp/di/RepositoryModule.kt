package com.example.kotlinfood2forkrecipeapp.di

import com.example.kotlinfood2forkrecipeapp.network.RecipeService
import com.example.kotlinfood2forkrecipeapp.network.model.RecipeDtoMapper
import com.example.kotlinfood2forkrecipeapp.repository.RecipeRepository
import com.example.kotlinfood2forkrecipeapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository {
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}

