package com.dmbdan.foodrecipes.domain.model


import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("totalResults")
    val totalResults: Int
)