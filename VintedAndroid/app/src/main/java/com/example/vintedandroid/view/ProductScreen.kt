package com.example.vintedandroid.view

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.client.models.BasicInsertionDto

@Composable
fun ProductScreen(searchedProduct: MutableState<BasicInsertionDto>) {


    val url = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png" //searchedProduct.value.imagePath

    val painter: ImagePainter = rememberImagePainter(url)


    Card(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Image(
            painter = painter,
            contentDescription = null, // Provide a proper content description
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
    Divider()
    Text(text = searchedProduct.value.title)
    Divider()
    searchedProduct.value.description?.let { Text(text = it) }
    Divider()
    searchedProduct.value.condition?.let { Text(text = it) }
    Divider()
    Text(text = "${searchedProduct.value.price}")
    Divider()
}

@Preview
@Composable
fun ProductScreenPreview() {
    var searchedProduct = remember {
        mutableStateOf(BasicInsertionDto(1L,"null", Float.MIN_VALUE,null,null,null,null,null,"",BasicInsertionDto.Brand.ADIDAS,BasicInsertionDto.Category.ABBIGLIAMENTO, 2L))
    }
    ProductScreen(searchedProduct)
}