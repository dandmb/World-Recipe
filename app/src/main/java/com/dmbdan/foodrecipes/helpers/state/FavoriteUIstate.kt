package com.dmbdan.foodrecipes.helpers.state

import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteEntity

data class FavoriteUIstate(
    val favoriteRecipes : List<FavoriteEntity> = listOf()
) {
}