package com.dmbdan.foodrecipes.data.datasource.remote

import com.dmbdan.foodrecipes.domain.model.RecipeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipeApi {
    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ):Response<RecipeModel>
}