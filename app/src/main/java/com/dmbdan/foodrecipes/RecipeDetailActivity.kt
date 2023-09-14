package com.dmbdan.foodrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dmbdan.foodrecipes.screens.mainscreens.RecipeDetail
import com.dmbdan.foodrecipes.ui.theme.FoodRecipesTheme

class RecipeDetailActivity : ComponentActivity() {


    /*
    companion object {
        private const val recipeId = "recipeId"
        fun intent(context: Context, recipe: Result) =
            Intent(context, RecipeDetailActivity::class.java).apply {
                putExtra(recipeId, recipe)
            }
    }

    private val recipe: Result by lazy {
        intent?.getSerializableExtra(recipeId) as Result
    }
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeDetail("recipe","","")
                }
            }
        }
    }
}
