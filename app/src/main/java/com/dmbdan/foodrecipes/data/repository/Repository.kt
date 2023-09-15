package com.dmbdan.foodrecipes.data.repository

import com.dmbdan.foodrecipes.data.datasource.local.LocalDataSource
import com.dmbdan.foodrecipes.data.datasource.local.database.RecipeEntity
import com.dmbdan.foodrecipes.data.datasource.remote.RemoteDataSource
import com.dmbdan.foodrecipes.helpers.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject


class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getRecipesRemotly(queries: Map<String, String>) = flow {
        emit(NetworkResult.Loading())
        val remoteRecipes = remoteDataSource.getRecipes(queries).body()
        if (remoteRecipes != null) {
            val recipeEntity = RecipeEntity(remoteRecipes)
            insertIntoDatabase(recipeEntity)
        }
        emit(NetworkResult.Success(remoteRecipes))
    }.catch {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Error(it.message))
    }

    suspend fun insertIntoDatabase(recipeEntity: RecipeEntity) {
        localDataSource.insertRecipes(recipeEntity)
    }

    // ChannelFlow: To emit a flow from another flow
    suspend fun getRecipesLocallyFromDb() = channelFlow{
        localDataSource.readDatabase()
            .mapNotNull {
                it[0].foodRecipe.results
            }
            .collectLatest {
                send(it)
            }
    }


}