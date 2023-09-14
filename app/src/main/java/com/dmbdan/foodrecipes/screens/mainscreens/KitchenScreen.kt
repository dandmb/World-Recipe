package com.dmbdan.foodrecipes.screens.mainscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showSystemUi = true)
fun KitchenScreen(){
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxSize().padding(5.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(5.dp).background(Color.Green).border(border = BorderStroke(width = 5.dp, color = Color.Black)).clickable{}
        ) {
            Text(text = "African", modifier = Modifier.align(Alignment.Center))
        }
        Box(
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(5.dp).background(Color.Yellow).border(border = BorderStroke(width = 5.dp, color = Color.Black)).clickable{}
        ) {
            Text(text = "European",modifier = Modifier.align(Alignment.Center))
        }
        Box(
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(5.dp).background(Color.Yellow).border(border = BorderStroke(width = 5.dp, color = Color.Black)).clickable{}
        ) {
            Text(text = "Asian",modifier = Modifier.align(Alignment.Center))
        }
        Box(
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(5.dp).background(Color.Yellow).border(border = BorderStroke(width = 5.dp, color = Color.Black)).clickable{}
        ) {
            Text(text = "Latin American",modifier = Modifier.align(Alignment.Center))
        }
        Box(
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(5.dp).background(Color.Yellow).border(border = BorderStroke(width = 5.dp, color = Color.Black)).clickable{}
        ) {
            Text(text = "Middle Eastern",modifier = Modifier.align(Alignment.Center))
        }
    }
}