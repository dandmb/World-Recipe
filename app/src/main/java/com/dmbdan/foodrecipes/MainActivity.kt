package com.dmbdan.foodrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dmbdan.foodrecipes.screens.MainScreen
import com.dmbdan.foodrecipes.ui.theme.FoodRecipesTheme
import com.dmbdan.foodrecipes.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodRecipesTheme {

                val viewModel: MainViewModel = hiltViewModel()
                val coroutineScope = rememberCoroutineScope()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        getRecipes = {
                            coroutineScope.launch {
                                viewModel.dataFromDB()
                            }
                        },
                        uiState = viewModel.uistate.value,
                        viewModel = viewModel,
                        favoriteUIstate=viewModel.favoriteUistate.value
                    )
                }
            }
        }
    }
}
