package com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.findNavController
import com.example.kotlinfood2forkrecipeapp.R
import com.example.kotlinfood2forkrecipeapp.presentation.components.RecipeList
import com.example.kotlinfood2forkrecipeapp.presentation.components.SearchAppBar
import com.example.kotlinfood2forkrecipeapp.presentation.theme.AppTheme
import com.example.kotlinfood2forkrecipeapp.util.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun RecipeListScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onNavigateToRecipeScreen: (String) -> Unit,
    viewModel: RecipeListViewModel
) {
    Log.d(TAG, "RecipeListScreen: $viewModel")
    
    val recipes = viewModel.recipes.value

    val query = viewModel.query.value

    val selectedCategory = viewModel.selectedCategory.value

    val loading = viewModel.loading.value

    val page = viewModel.page.value

    val scaffoldState = rememberScaffoldState()

    AppTheme(
        displayProgressBar = loading,
        scaffoldState = scaffoldState,
        darkTheme = isDarkTheme,
    ) {
        Scaffold(
            topBar = {
                SearchAppBar(
                    query = query,
                    onQueryChanged = viewModel::onQueryChanged,
                    onExecuteSearch = {
                        viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent)
                    },
                    categories = getAllFoodCategories(),
                    selectedCategory = selectedCategory,
                    onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                    onToggleTheme = { onToggleTheme() }
                )
            },
            scaffoldState = scaffoldState,
            snackbarHost = {
                scaffoldState.snackbarHostState
            },

            ) {
            RecipeList(
                loading = loading,
                recipes = recipes,
                onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                page = page,
                onTriggerNextPage = { viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent) },
                onNavigateToRecipeDetailScreen = {
                    onNavigateToRecipeScreen
                }
            )
        }
    }
}