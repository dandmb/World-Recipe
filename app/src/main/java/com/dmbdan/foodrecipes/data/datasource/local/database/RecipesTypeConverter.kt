package com.dmbdan.foodrecipes.data.datasource.local.database

import androidx.room.TypeConverter
import com.dmbdan.foodrecipes.domain.model.RecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {
    var gson=Gson()
    @TypeConverter
    fun foodRecipeToString(foodRecipeModel: RecipeModel):String{
        return gson.toJson(foodRecipeModel)
    }

    @TypeConverter
    fun stringToFoodRecipe(data:String):RecipeModel{
        val listType = object : TypeToken<RecipeModel>(){}.type
        return gson.fromJson(data,listType)
    }
}