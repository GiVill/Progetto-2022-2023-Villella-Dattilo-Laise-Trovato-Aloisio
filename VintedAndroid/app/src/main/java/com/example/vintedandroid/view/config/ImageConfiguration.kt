package com.example.vintedandroid.view.config

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

const val fixedUrlForImage = "https://192.168.1.90:8010/vintedProject-api/v1/images/"
const val logTag = "ImageConfiguration::class"

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageConfiguration(imageName: String?, imageScale: ContentScale){ //painter: ImagePainter

    val painter: ImagePainter = rememberImagePainter("$fixedUrlForImage$imageName")

    Log.i(logTag, "url: $fixedUrlForImage$imageName")

    Box(modifier = Modifier.fillMaxWidth()) {
        //Log.i(logTag, "Image status: ${painter.state.painter.toString()}")
        //if (painter.state.painter!=null) { //painter.state != ImagePainter.State.Empty
            Image(
                painter = painter,
                contentDescription = "Image of the product",
                modifier = Modifier.fillMaxWidth(),
                contentScale = imageScale,

                )
        //} else {
        //Text(text = "Error while loading the image")
        //}

    }
}
