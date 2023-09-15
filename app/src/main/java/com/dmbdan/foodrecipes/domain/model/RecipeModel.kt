package com.dmbdan.foodrecipes.domain.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeModel(
    @SerializedName("results")
    val results: List<Result>
):Serializable