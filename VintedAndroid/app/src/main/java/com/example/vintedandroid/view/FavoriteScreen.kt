package com.example.vintedandroid.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteScreen() {
    Text(text = "Cambiami! Mi trovo nel package view e sono in FavoriteScreen.kt")
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen()
}