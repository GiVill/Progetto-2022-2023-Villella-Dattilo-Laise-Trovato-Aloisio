package com.example.vintedandroid.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FeedbackActivity() {
    Text(text = "Cambiami! Mi trovo nel package view e sono in FeedbackActivity.kt")
}

@Preview
@Composable
fun FeedbackScreenPreview() {
    FeedbackActivity()
}