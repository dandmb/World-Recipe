package com.dmbdan.foodrecipes.domain.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Length(
    @SerializedName("number")
    val number: Int,
    @SerializedName("unit")
    val unit: String
):Serializable