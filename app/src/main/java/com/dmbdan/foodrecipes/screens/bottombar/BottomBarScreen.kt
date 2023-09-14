package com.dmbdan.foodrecipes.screens.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

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
}
