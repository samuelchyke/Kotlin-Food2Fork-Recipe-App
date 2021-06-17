package com.example.kotlinfood2forkrecipeapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinfood2forkrecipeapp.R
import com.example.kotlinfood2forkrecipeapp.presentation.navigation.Screen
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe_list.RecipeListScreen
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.HiltViewModelFactory
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe.RecipeDetailScreen
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe.RecipeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent{
      val navController = rememberNavController()
      NavHost(navController = navController, startDestination = Screen.RecipeList.route) {

        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
          val factory = HiltViewModelFactory(AmbientContext.current, navBackStackEntry)
          val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
          RecipeListScreen(
            isDarkTheme = (application as BaseApplication).isDark.value,
            onToggleTheme = (application as BaseApplication)::toggleLightTheme,
            viewModel = viewModel,
          )
        }

        composable(route = Screen.RecipeDetail.route) { navBackStackEntry ->
          val factory = HiltViewModelFactory(AmbientContext.current, navBackStackEntry)
          val viewModel: RecipeViewModel = viewModel("RecipeDetailViewModel", factory)
          RecipeDetailScreen(
            isDarkTheme = (application as BaseApplication).isDark.value,
            recipeId = 1, // hard code for now
            viewModel = viewModel,
          )
        }

      }
    }

  }


}


