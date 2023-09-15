package com.dmbdan.foodrecipes.data.datasource.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmbdan.foodrecipes.domain.model.RecipeModel
import com.dmbdan.foodrecipes.helpers.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipeEntity(
    var foodRecipe: RecipeModel
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}