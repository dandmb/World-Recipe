package com.dmbdan.foodrecipes.screens.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.dmbdan.foodrecipes.helpers.Constants.Companion.RECIPE_IMAGE
import com.dmbdan.foodrecipes.helpers.Constants.Companion.RECIPE_SUMMARY
import com.dmbdan.foodrecipes.helpers.Constants.Companion.RECIPE_TITLE


sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:ImageVector
){
    object Recipe: BottomBarScreen(
        route = "home",
        title = "Recipes",
        icon= Icons.Default.List
    )
    object Favorite: BottomBarScreen(
        route = "favorite",
        title = "Favorite",
        icon= Icons.Default.Favorite
    )
    object Setting: BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon= Icons.Default.Settings
    )
    object RecipeDetail: BottomBarScreen(
        route = "home/{$RECIPE_IMAGE}/{$RECIPE_TITLE}/{$RECIPE_SUMMARY}",
        title = "Recipes",
        icon= Icons.Default.List
    ){
        fun passArg(image:String,title: String,summary:String):String{
            return "home/$image/$title/$summary"
        }
    }
}
