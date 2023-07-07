package com.example.vintedandroid.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddScreen() {
    Text(text = "Cambiami! Mi trovo nel package view e sono in AddScreen.kt")
}

@Preview
@Composable
fun AddScreenPreview() {
    AddScreen()
}