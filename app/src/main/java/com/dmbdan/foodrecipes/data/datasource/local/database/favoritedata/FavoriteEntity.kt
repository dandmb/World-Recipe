package com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmbdan.foodrecipes.helpers.Constants.Companion.FAVORITE_TABLE

@Entity(tableName = FAVORITE_TABLE)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String,
    val title: String,
    val summary: String
)