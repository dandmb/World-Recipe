package com.dmbdan.foodrecipes.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dmbdan.foodrecipes.domain.model.Result
import com.dmbdan.foodrecipes.helpers.state.UIState
import com.dmbdan.foodrecipes.screens.bottombar.BottomBarScreen
import com.dmbdan.foodrecipes.screens.mainscreens.FavoriteScreen
import com.dmbdan.foodrecipes.screens.mainscreens.RecipeScreen
import com.dmbdan.foodrecipes.screens.mainscreens.SettingScreen

@Composable
fun BottomNavGraph(navHostController: NavHostController,modifier: Modifier=Modifier,uiState: UIState,selectedItem: (Result) -> Unit){
    NavHost(navController = navHostController, startDestination = BottomBarScreen.Recipe.route,modifier){
        composable(route= BottomBarScreen.Recipe.route){
            RecipeScreen(uiState,selectedItem)
        }
        composable(route= BottomBarScreen.Favorite.route){
            FavoriteScreen()
        }
        composable(route= BottomBarScreen.Setting.route){
            SettingScreen()
        }
    }
}