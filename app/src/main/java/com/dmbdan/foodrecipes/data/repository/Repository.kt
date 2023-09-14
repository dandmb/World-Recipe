package com.dmbdan.foodrecipes.data.repository

import com.dmbdan.foodrecipes.data.datasource.remote.RemoteDataSource
import com.dmbdan.foodrecipes.helpers.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getRecipes(queries: Map<String, String>)= flow{
        emit(NetworkResult.Loading())
        val remoteRecipes= remoteDataSource.getRecipes(queries).body()
        emit(NetworkResult.Success(remoteRecipes))
    }.catch {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Error(it.message))
    }

}