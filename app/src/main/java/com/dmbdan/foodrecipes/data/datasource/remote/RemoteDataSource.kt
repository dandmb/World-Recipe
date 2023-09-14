package com.dmbdan.foodrecipes.data.datasource.remote

import com.dmbdan.foodrecipes.domain.model.RecipeModel
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipeApi: FoodRecipeApi
) {
    suspend fun getRecipes(queries: Map<String, String>):Response<RecipeModel>{
        return foodRecipeApi.getRecipes(queries)
    }
}