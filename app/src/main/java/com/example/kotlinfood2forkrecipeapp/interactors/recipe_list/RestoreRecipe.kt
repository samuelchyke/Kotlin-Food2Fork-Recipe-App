package com.example.kotlinfood2forkrecipeapp.interactors.recipe_list

import android.util.Log
import com.example.kotlinfood2forkrecipeapp.cache.RecipeDao
import com.example.kotlinfood2forkrecipeapp.cache.model.RecipeEntityMapper
import com.example.kotlinfood2forkrecipeapp.domain.data.DataState
import com.example.kotlinfood2forkrecipeapp.domain.model.Recipe
import com.example.kotlinfood2forkrecipeapp.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class RestoreRecipes(

    private val recipeDao: RecipeDao,
    private val entityMapper: RecipeEntityMapper,
) {

    fun execute(
        page: Int,
        query: String
    ): Flow<DataState<List<Recipe>>> = flow {
        try {
            emit(DataState.loading())

            // just to show pagination, api is fast
            delay(1000)

            // query the cache
            val cacheResult = if (query.isBlank()){
                recipeDao.restoreAllRecipes(
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            }
            else{
                recipeDao.restoreRecipes(
                    query = query,
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            }

            // emit List<Recipe> from cache
            val list = entityMapper.fromEntityList(cacheResult)
            emit(DataState.success(list))

        }catch (e: Exception){
            emit(DataState.error<List<Recipe>>(e.message?: "Unknown Error"))
        }
    }

}