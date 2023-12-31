package com.dmbdan.foodrecipes.screens.mainscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dmbdan.foodrecipes.helpers.ItemImage
import com.dmbdan.foodrecipes.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch


@Composable
fun RecipeDetail(
    recipeImage: String,
    recipeTitle: String,
    recipeSummary: String,
    viewModel: MainViewModel
) {
    val scrollState= rememberScrollState()
    var iconState by rememberSaveable {
        mutableStateOf(true)
    }
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll((scrollState))
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            ItemImage(
                recipeResponseImage = recipeImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = recipeTitle.uppercase(),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Save",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        coroutineScope.launch {
                            viewModel.addFavoriteRecipe(recipeImage,recipeTitle,recipeSummary)
                        }
                        iconState=!iconState
                    },
                tint = if (iconState) Color.Red else Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = recipeSummary,
                style = TextStyle(
                    textAlign = TextAlign.Justify,
                    fontStyle = FontStyle.Italic
                    )
            )

        }
    }
}
