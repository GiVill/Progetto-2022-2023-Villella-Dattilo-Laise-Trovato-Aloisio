package com.example.vintedandroid.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FeedbackScreen() {
    Text(text = "Cambiami! Mi trovo nel package view e sono in FeedbackScreen.kt")
}

@Preview
@Composable
fun FeedbackScreenPreview() {
    FeedbackScreen()
}