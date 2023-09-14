package com.dmbdan.foodrecipes.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ItemImage(recipeResponseImage: String, modifier: Modifier=Modifier, contentScale: ContentScale = ContentScale.FillHeight) {
    AsyncImage(
        model = recipeResponseImage,
        contentDescription = "Recipe Image",
        contentScale = contentScale,
        modifier = modifier
    )
}
