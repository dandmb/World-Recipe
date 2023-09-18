package com.dmbdan.foodrecipes.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.Html
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteEntity
import com.dmbdan.foodrecipes.data.repository.FavoriteRecipeRepository
import com.dmbdan.foodrecipes.data.repository.Repository
import com.dmbdan.foodrecipes.helpers.Constants.Companion.API_KEY
import com.dmbdan.foodrecipes.helpers.NetworkResult
import com.dmbdan.foodrecipes.helpers.state.FavoriteUIstate
import com.dmbdan.foodrecipes.helpers.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val favoriteRecipeRepository: FavoriteRecipeRepository,
    application: Application
) : AndroidViewModel(application) {

    val uistate = mutableStateOf(UIState())
    val favoriteUistate = mutableStateOf(FavoriteUIstate())

    suspend fun dataFromDB() {
        repository.getRecipesLocallyFromDb().collectLatest {
            Log.d("In ViewModel", "$it")
            if (it.isEmpty()) {
                uistate.value = UIState(error = "ERROR")
            } else {
                uistate.value = UIState(isLoading = false, recipes = it)
            }
        }
    }

    suspend fun favoriteData(){
        favoriteRecipeRepository.getFavoriteRecipes().collect{
            Log.d("Favorite data","$it")
            favoriteUistate.value=FavoriteUIstate(favoriteRecipes = it)
        }
    }
    private suspend fun saveFavoriteRecipe(favoriteEntity: FavoriteEntity){
        favoriteRecipeRepository.insertIntoFavoriteDatabase(favoriteEntity)
    }

    suspend fun addFavoriteRecipe(image:String, title: String,summary: String){
        val entity=FavoriteEntity(0,image,title,summary)
        saveFavoriteRecipe(entity)
        favoriteData()
    }

    init {
        requestApiData()
        viewModelScope.launch {
            favoriteData()
        }


    }

    fun requestApiData(
        query: String = "bread",
        cuisine: String = "asian",
        diet: String = "Ketogenic",
        intolerances: String = "Gluten"
    ) {
        getRecipes(applyQueries(query, cuisine, diet, intolerances))
    }

    private fun applyQueries(
        query: String,
        cuisine: String,
        diet: String,
        intolerances: String
    ): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries["query"] = query
        queries["number"] = "10"
        queries["apiKey"] = API_KEY
        queries["cuisine"] = cuisine
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"
        queries["diet"] = diet
        queries["intolerances"] = intolerances


        return queries
    }


    private fun getRecipes(queries: Map<String, String>) {
        viewModelScope.launch {
            repository.getRecipesRemotly(queries).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        uistate.value = UIState(isLoading = true)
                    }

                    is NetworkResult.Success -> {
                        uistate.value =
                            UIState(isLoading = false, recipes = result.data!!.results)

                    }

                    else -> {
                        uistate.value = UIState(error = result.message)
                    }
                }

            }
        }
    }

    fun encodeImgUrl(imgLink: String): String {
        return URLEncoder.encode(imgLink, StandardCharsets.UTF_8.toString())
    }

    fun removeSlash(title: String): String {
        return title.replace("/", "-")
    }

    fun convertHtmlToString(summary: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(
            summary,
            Html.FROM_HTML_MODE_LEGACY
        ).toString() else Html.fromHtml(summary).toString()
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

