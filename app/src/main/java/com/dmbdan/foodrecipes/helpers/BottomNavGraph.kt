package com.dmbdan.foodrecipes.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dmbdan.foodrecipes.helpers.state.FavoriteUIstate
import com.dmbdan.foodrecipes.helpers.state.UIState
import com.dmbdan.foodrecipes.screens.bottombar.BottomBarScreen
import com.dmbdan.foodrecipes.screens.mainscreens.FavoriteScreen
import com.dmbdan.foodrecipes.screens.mainscreens.RecipeDetail
import com.dmbdan.foodrecipes.screens.mainscreens.RecipeScreen
import com.dmbdan.foodrecipes.screens.mainscreens.SettingScreen
import com.dmbdan.foodrecipes.ui.viewmodel.MainViewModel

@Composable
fun BottomNavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    uiState: UIState,
    viewModel: MainViewModel,
    favoriteUIstate: FavoriteUIstate
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.Recipe.route,
        modifier
    ) {
        composable(route = BottomBarScreen.Recipe.route) {
            RecipeScreen(uiState, navController = navHostController, viewModel)
        }
        composable(route = BottomBarScreen.Favorite.route) {
            FavoriteScreen(favoriteUIstate)
        }
        composable(route = BottomBarScreen.Setting.route) {
            SettingScreen()
        }
        composable(
            route = BottomBarScreen.RecipeDetail.route,
            arguments = listOf(
                navArgument(Constants.RECIPE_IMAGE) {
                    type = NavType.StringType

                },
                navArgument(Constants.RECIPE_TITLE) {
                    type = NavType.StringType

                },
                navArgument(Constants.RECIPE_SUMMARY) {
                    type = NavType.StringType

                }
            )
        ) {
            //Log.d("Argumentsss",it.arguments?.getString(Constants.RECIPE_IMAGE).toString())
            val imgSrc = it.arguments?.getString(Constants.RECIPE_IMAGE).toString()
            val title = it.arguments?.getString(Constants.RECIPE_TITLE).toString()
            val summary = it.arguments?.getString(Constants.RECIPE_SUMMARY).toString()
            RecipeDetail(imgSrc, title, summary, viewModel)
        }
    }
}