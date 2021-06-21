package com.example.kotlinfood2forkrecipeapp.interactors.recipe

import com.example.kotlinfood2forkrecipeapp.cache.RecipeDao
import com.example.kotlinfood2forkrecipeapp.cache.model.RecipeEntityMapper
import com.example.kotlinfood2forkrecipeapp.domain.data.DataState
import com.example.kotlinfood2forkrecipeapp.domain.model.Recipe
import com.example.kotlinfood2forkrecipeapp.network.RecipeService
import com.example.kotlinfood2forkrecipeapp.network.model.RecipeDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class GetRecipe ( private val recipeDao: RecipeDao,
private val entityMapper: RecipeEntityMapper,
private val recipeService: RecipeService,
private val recipeDtoMapper: RecipeDtoMapper,
){

    fun execute(
        recipeId: Int,
        token: String,
    ): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())

            // just to show loading, cache is fast
            delay(1000)

            var recipe = getRecipeFromCache(recipeId = recipeId)

            if(recipe != null){
                emit(DataState.success(recipe))
            }
            // if the recipe is null, it means it was not in the cache for some reason. So get from network.
            else{

                // TODO("Check if there is an internet connection")
                // get recipe from network
                val networkRecipe = getRecipeFromNetwork(token, recipeId) // dto -> domain

                // insert into cache
                recipeDao.insertRecipe(
                    // map domain -> entity
                    entityMapper.mapFromDomainModel(networkRecipe)
                )

                // get from cache
                recipe = getRecipeFromCache(recipeId = recipeId)

                // emit and finish
                if(recipe != null){
                    emit(DataState.success(recipe))
                }
                else{
                    throw Exception("Unable to get recipe from the cache.")
                }
            }

        }catch (e: Exception){
            emit(DataState.error<Recipe>(e.message?: "Unknown Error"))
        }
    }

    private suspend fun getRecipeFromCache(recipeId: Int): Recipe? {
        return recipeDao.getRecipeById(recipeId)?.let { recipeEntity ->
            entityMapper.mapToDomainModel(recipeEntity)
        }
    }

    private suspend fun getRecipeFromNetwork(token: String, recipeId: Int): Recipe {
        return recipeDtoMapper.mapToDomainModel(recipeService.get(token, recipeId))
    }

}


