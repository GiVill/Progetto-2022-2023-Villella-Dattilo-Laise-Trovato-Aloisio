package com.example.vintedandroid.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

private val Green40 = Color(0xFF177245)
private val Green80 = Color(0xFF177245)
private val LightGreen40 = Color(0xFFBFFF00)

@Composable
fun VintedAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Green40,
            secondary = Green80,

        )
    } else {
        lightColors(
            primary = Green40,
            secondary = Green80,

        )
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}

@Composable
fun MenuItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = text)
        }
    }
}
