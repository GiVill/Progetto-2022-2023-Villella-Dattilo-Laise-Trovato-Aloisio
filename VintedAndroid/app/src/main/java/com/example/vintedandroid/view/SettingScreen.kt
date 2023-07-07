package com.example.vintedandroid.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingScreen() {
    Text(text = "Cambiami! Mi trovo nel package view e sono in SettingScreen.kt")
}

@Preview
@Composable
fun SettingScreenPreview() {
    SettingScreen()
}