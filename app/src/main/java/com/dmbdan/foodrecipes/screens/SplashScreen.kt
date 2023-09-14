package com.dmbdan.foodrecipes.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dmbdan.foodrecipes.ui.theme.Teal200

@Composable
@Preview()
fun FirstScreen(modifier: Modifier=Modifier){
    Box (modifier= Modifier
        .fillMaxSize()
        .background(Teal200)){

        Text(text = "Food Recipes",modifier=Modifier.align(Alignment.Center))
    }
}