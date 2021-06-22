package com.example.kotlinfood2forkrecipeapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.kotlinfood2forkrecipeapp.presentation.navigation.Screen
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe.RecipeDetailScreen
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe.RecipeDetailViewModel
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe_list.RecipeListScreen
import com.example.kotlinfood2forkrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.example.kotlinfood2forkrecipeapp.presentation.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

  @Inject
  lateinit var connectivityManager: ConnectivityManager
  override fun onStart() {
    super.onStart()
    connectivityManager.registerConnectionObserver(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    connectivityManager.unregisterConnectionObserver(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
          val factory = HiltViewModelFactory(AmbientContext.current, navBackStackEntry)
          val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
          RecipeListScreen(
            isDarkTheme = (application as BaseApplication).isDark.value,
            onToggleTheme = (application as BaseApplication)::toggleLightTheme,
            onNavigateToRecipeDetailScreen = navController::navigate,
            viewModel = viewModel,
          )
        }

        composable(
          route = Screen.RecipeDetail.route + "/{recipeId}",
          arguments = listOf(navArgument("recipeId") {
            type = NavType.IntType
          })
        ) { navBackStackEntry ->
          val factory = HiltViewModelFactory(AmbientContext.current, navBackStackEntry)
          val detailViewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel", factory)
          RecipeDetailScreen(
            isDarkTheme = (application as BaseApplication).isDark.value,
            recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
            viewModel = detailViewModel,
          )
        }
      }
    }
  }


}


