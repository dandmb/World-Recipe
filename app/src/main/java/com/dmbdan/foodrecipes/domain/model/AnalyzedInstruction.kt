package com.dmbdan.foodrecipes.domain.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnalyzedInstruction(
    @SerializedName("name")
    val name: String,
    @SerializedName("steps")
    val steps: List<Step>
):Serializable