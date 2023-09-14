package com.dmbdan.foodrecipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dmbdan.foodrecipes.domain.model.Result
import com.dmbdan.foodrecipes.helpers.ItemImage
import com.dmbdan.foodrecipes.ui.theme.FoodRecipesTheme

class RecipeDetailActivity : ComponentActivity() {


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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeDetail(recipe)
                }
            }
        }
    }
}

@Composable
fun RecipeDetail(recipe: Result) {
    val scrollState= rememberScrollState()
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll((scrollState))
                .padding(10.dp)
        ) {
            ItemImage(
                recipeResponseItem = recipe,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = recipe.title.uppercase(),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = recipe.summary,
                style = TextStyle(
                    textAlign = TextAlign.Justify,

                )
            )
        }
    }
}
