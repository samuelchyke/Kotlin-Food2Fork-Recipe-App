package com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinfood2forkrecipeapp.presentation.components.LoadingRecipeShimmer
import com.example.kotlinfood2forkrecipeapp.presentation.components.RecipeView
import com.example.kotlinfood2forkrecipeapp.presentation.theme.AppTheme
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.example.kotlinfood2forkrecipeapp.util.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    recipeId: Int?,
    viewModel: RecipeViewModel
) {
    Log.d(TAG, "RecipeDetailScreen: $viewModel")
    Text("RecipeDetailScreen: $viewModel")
//
//    val loading = viewModel.loading.value
//
//    val recipe = viewModel.recipe.value
//
//    val scaffoldState = rememberScaffoldState()
//
//    AppTheme(
//        displayProgressBar = loading,
//        scaffoldState = scaffoldState,
//        darkTheme = isDarkTheme,
//    ){
//        Scaffold(
//            scaffoldState = scaffoldState,
//            snackbarHost = {
//                scaffoldState.snackbarHostState
//            }
//        ) {
//            Box (
//                modifier = Modifier.fillMaxSize()
//            ){
//                if (loading && recipe == null) LoadingRecipeShimmer(imageHeight = IMAGE_HEIGHT.dp)
//                else recipe?.let {
//                    if(it.id == 1) { // force an error to demo snackbar
//                        snackbarController.getScope().launch {
//                            snackbarController.showSnackbar(
//                                scaffoldState = scaffoldState,
//                                message = "An error occurred with this recipe",
//                                actionLabel = "Ok"
//                            )
//                        }
//                    }
//                    else{
//                        RecipeView(
//                            recipe = it,
//                        )
//                    }
//                }
//            }
//        }
//    }
}