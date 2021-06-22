package com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinfood2forkrecipeapp.presentation.components.IMAGE_HEIGHT
import com.example.kotlinfood2forkrecipeapp.presentation.components.LoadingRecipeShimmer
import com.example.kotlinfood2forkrecipeapp.presentation.components.RecipeView
import com.example.kotlinfood2forkrecipeapp.presentation.theme.AppTheme
import com.example.kotlinfood2forkrecipeapp.util.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    recipeId: Int?,
    viewModel: RecipeDetailViewModel,
) {
    if (recipeId == null) {
        //error
    } else {
        val onLoad = viewModel.onLoad.value
        if (!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
        }
        val loading = viewModel.loading.value

        val recipe = viewModel.recipe.value

        val dialogQueue = viewModel.dialogQueue

        val scaffoldState = rememberScaffoldState()

        AppTheme(
            displayProgressBar = loading,
            scaffoldState = scaffoldState,
            darkTheme = isDarkTheme,
            isNetworkAvailable = isNetworkAvailable,
            dialogQueue = dialogQueue.queue.value,
        ){
            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    scaffoldState.snackbarHostState
                }
            ) {
                Box (
                    modifier = Modifier.fillMaxSize()
                ){
                    if (loading && recipe == null) {
                        LoadingRecipeShimmer(imageHeight = IMAGE_HEIGHT.dp)
                    }
                    else if(!loading && recipe == null && onLoad){
                        TODO("Show Invalid Recipe")
                    }
                    else {
                        recipe?.let {RecipeView(recipe = it) }
                    }
                }
            }
        }
    }
}