package com.example.vintedandroid.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteScreen() {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "Cambiami! Mi trovo nel package view e sono in FavoriteScreen.kt")

        ItemInFavorite()
        ItemInFavorite()

    }
}

@Composable
private fun ItemInFavorite(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { /* Open item details activity */ }),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Item a caso nei preferiti")
        }
    }
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen()
}