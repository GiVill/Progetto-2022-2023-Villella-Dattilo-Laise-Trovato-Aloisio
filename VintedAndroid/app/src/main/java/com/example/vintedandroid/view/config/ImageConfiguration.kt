package com.example.vintedandroid.view.config

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageConfiguration(painter: ImagePainter, imageScale: ContentScale){
    if (painter.state != ImagePainter.State.Empty) {
        Image(
            painter = painter,
            contentDescription = "Image of the product" ,
            modifier = Modifier.fillMaxWidth(),
            contentScale = imageScale
        )
    } else { Text(text = "Error while loading the image") }
}