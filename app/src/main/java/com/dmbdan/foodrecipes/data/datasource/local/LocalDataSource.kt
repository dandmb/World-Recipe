package com.dmbdan.foodrecipes.data.datasource.local

import com.dmbdan.foodrecipes.data.datasource.local.database.RecipeDao
import com.dmbdan.foodrecipes.data.datasource.local.database.RecipeEntity
import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteDao
import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao,
    private val favoriteDao: FavoriteDao
){
    suspend fun insertRecipes(recipeEntity: RecipeEntity){
        recipeDao.insertRecipe(recipeEntity)
    }

    fun readDatabase():Flow<List<RecipeEntity>>{
        return recipeDao.readRecipes()
    }

    suspend fun inserFavoritetRecipes(favoriteEntity: FavoriteEntity){
        favoriteDao.insertFavoriteRecipe(favoriteEntity)
    }

    fun readFavoriteData():Flow<List<FavoriteEntity>>{
        return favoriteDao.readFavoriteRecipes()
    }

}