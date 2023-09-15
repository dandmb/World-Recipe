package com.dmbdan.foodrecipes.data.datasource.local

import com.dmbdan.foodrecipes.data.datasource.local.database.RecipeDao
import com.dmbdan.foodrecipes.data.datasource.local.database.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao
){
    suspend fun insertRecipes(recipeEntity: RecipeEntity){
        recipeDao.insertRecipe(recipeEntity)
    }

    fun readDatabase():Flow<List<RecipeEntity>>{
        return recipeDao.readRecipes()
    }
}