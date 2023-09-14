package com.dmbdan.foodrecipes.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dmbdan.foodrecipes.data.repository.Repository
import com.dmbdan.foodrecipes.helpers.Constants.Companion.API_KEY
import com.dmbdan.foodrecipes.helpers.NetworkResult
import com.dmbdan.foodrecipes.helpers.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val uistate = mutableStateOf(UIState())

    init {
        requestApiData()
    }

    fun requestApiData(
        query: String = "rice",
        cuisine: String = "asian"
    ) {
        getRecipes(applyQueries(query, cuisine))
    }

    private fun applyQueries(
        query: String,
        cuisine: String
    ): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries["query"] = query
        queries["number"] = "10"
        queries["apiKey"] = API_KEY
        queries["cuisine"] = cuisine
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"

        return queries
    }


    private fun getRecipes(queries: Map<String, String>) {
        viewModelScope.launch {
            repository.getRecipes(queries).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        uistate.value = UIState(isLoading = true)
                    }

                    is NetworkResult.Success -> {
                        uistate.value =
                            UIState(isLoading = false, recipes = result.data!!.results)
                        Log.d("My data: ", "${uistate.value.recipes.size}")
                    }

                    else -> {
                        uistate.value = UIState(error = result.message)
                    }
                }

            }
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}