package com.dmbdan.foodrecipes.screens.mainscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dmbdan.foodrecipes.R
import com.dmbdan.foodrecipes.domain.model.Result
import com.dmbdan.foodrecipes.helpers.ItemImage
import com.dmbdan.foodrecipes.helpers.state.UIState
import com.dmbdan.foodrecipes.screens.NoConnection
import com.dmbdan.foodrecipes.screens.bottombar.BottomBarScreen
import com.dmbdan.foodrecipes.ui.viewmodel.MainViewModel

@Composable
fun RecipeScreen(
    uiState: UIState,
    navController: NavController,
    viewModel: MainViewModel
) {
    val recipeItems = uiState.recipes
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (!uiState.error.isNullOrEmpty()) {
            NoConnection()
        } else {
            LazyColumn {
                items(
                    items = recipeItems,
                    itemContent = {
                        RecipeCard(recipeResponseItem = it,navController,viewModel)
                    }
                )
            }
        }
    }
}


@Composable
fun RecipeCard(
    recipeResponseItem: Result,
    navController: NavController,
    viewModel: MainViewModel
) {
    val title=viewModel.removeSlash(recipeResponseItem.title)
    val encodedUrlImg = viewModel.encodeImgUrl(recipeResponseItem.image)
    val summary = viewModel.convertHtmlToString(recipeResponseItem.summary)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(200.dp)
            .clickable {
                navController.navigate(route = BottomBarScreen.RecipeDetail.passArg(encodedUrlImg,title,summary))
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            ItemImage(
                recipeResponseItem.image,
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = recipeResponseItem.title, style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = recipeResponseItem.summary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                LastLine(recipeResponseItem.aggregateLikes, recipeResponseItem.healthScore)
            }

        }
    }
}

@Composable
fun LastLine(numberOfLikes: Int, healthScore: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (numberOfLikes > 0) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_favorite_24),
                    contentDescription = "Favorite",
                    tint = Color.Green
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_favorite_24),
                    contentDescription = "Favorite",
                    tint = Color.Red
                )
            }
            Text(text = "$numberOfLikes",style = TextStyle(fontWeight = FontWeight.Bold))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_score_24),
                contentDescription = "Health Score"
            )
            Text(text = "$healthScore", style = TextStyle(fontWeight = FontWeight.Bold))
        }
    }
}