package com.dmbdan.foodrecipes.data.repository

import com.dmbdan.foodrecipes.data.datasource.local.LocalDataSource
import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteEntity
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class FavoriteRecipeRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend fun insertIntoFavoriteDatabase(favoriteEntity: FavoriteEntity) {
        localDataSource.inserFavoritetRecipes(favoriteEntity)
    }


    suspend fun getFavoriteRecipes() = channelFlow{
        localDataSource.readFavoriteData().collectLatest {
                send(it)
            }
    }

}