package com.dmbdan.foodrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dmbdan.foodrecipes.screens.MainScreen
import com.dmbdan.foodrecipes.ui.theme.FoodRecipesTheme
import com.dmbdan.foodrecipes.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodRecipesTheme {
                // A surface container using the 'background' color from the theme
                val viewModel:MainViewModel= hiltViewModel()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        getRecipes = {
                            viewModel.requestApiData()
                        },
                        uiState = viewModel.uistate.value,
                        selectedItem = {
                            //Toast.makeText(this,it.title, Toast.LENGTH_SHORT).show()
                            startActivity(RecipeDetailActivity.intent(this,it))

                        }
                    )
                }
            }
        }
    }
}
