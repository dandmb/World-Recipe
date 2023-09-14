package com.dmbdan.foodrecipes.domain.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Equipment(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("localizedName")
    val localizedName: String,
    @SerializedName("name")
    val name: String
):Serializable