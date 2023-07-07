package com.example.vintedandroid.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchScreen() {
    Text(text = "Cambiami! Mi trovo nel package view e sono in SearchScreen.kt")
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}