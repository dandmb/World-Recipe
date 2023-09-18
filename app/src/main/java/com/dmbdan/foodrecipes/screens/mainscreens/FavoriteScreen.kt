package com.dmbdan.foodrecipes.screens.mainscreens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmbdan.foodrecipes.R
import com.dmbdan.foodrecipes.data.datasource.local.database.favoritedata.FavoriteEntity
import com.dmbdan.foodrecipes.helpers.ItemImage
import com.dmbdan.foodrecipes.helpers.state.FavoriteUIstate


@Composable
fun FavoriteScreen(
    favoriteUIstate: FavoriteUIstate
) {
    val favoritetItems = favoriteUIstate.favoriteRecipes

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (favoritetItems.isEmpty()){
            NoFavorite()
        }else{
            LazyColumn {
                items(
                    items = favoritetItems,
                    itemContent = {
                        FavoriteCard(recipeFavoriteItem = it)
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteCard(
    recipeFavoriteItem: FavoriteEntity
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            ItemImage(
                recipeFavoriteItem.image,
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp)
            ) {
                Text(
                    text = recipeFavoriteItem.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
                Text(
                    text = recipeFavoriteItem.summary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 7,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f)
                )
            }

        }
    }
}
@Composable
@Preview
fun NoFavorite(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painter = painterResource(id = R.drawable.baseline_mood_bad_24), contentDescription = "No Internet Connection", modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "There are no favorite")
        }
    }
}