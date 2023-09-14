package com.dmbdan.foodrecipes.domain.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Step(
    @SerializedName("equipment")
    val equipment: List<Equipment>,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>,
    @SerializedName("length")
    val length: Length,
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String
):Serializable