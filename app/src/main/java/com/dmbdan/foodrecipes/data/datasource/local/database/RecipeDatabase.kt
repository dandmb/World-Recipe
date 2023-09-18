package com.dmbdan.foodrecipes.data.datasource.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteDao
import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteEntity

@Database(
    entities = [RecipeEntity::class,FavoriteEntity::class],
    version = 2,
    autoMigrations = [AutoMigration(from = 1, to =2)]
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipeDatabase:RoomDatabase() {
    abstract fun recipesDao(): RecipeDao
    abstract fun favoriteRecipesDao(): FavoriteDao
}