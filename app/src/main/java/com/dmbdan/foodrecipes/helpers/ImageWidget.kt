package com.dmbdan.foodrecipes.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.dmbdan.foodrecipes.domain.model.Result

@Composable
fun ItemImage(recipeResponseItem: Result,modifier: Modifier=Modifier,contentScale: ContentScale = ContentScale.FillHeight) {
    AsyncImage(
        model = recipeResponseItem.image,
        contentDescription = "Recipe Image",
        contentScale = contentScale,
        modifier = modifier
    )
}
