package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.example.vintedandroid.client.models.BasicInsertionDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.view.config.ImageConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoilApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun ProductScreen(searchedProduct: MutableState<BasicInsertionDto>) {

    val url = "https://192.168.1.90:8010/vintedProject-api/v1/images/${searchedProduct.value.imageName}"//"https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png" //searchedProduct.value.imagePath

    val painter: ImagePainter = rememberImagePainter(url)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "I colori sono tutti sbagliati :)")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ImageConfiguration(painter = painter, ContentScale.Fit)

                Text(
                    text = searchedProduct.value.title,
                    modifier = Modifier.padding(16.dp), fontSize = 30.sp
                )
                searchedProduct.value.description?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                searchedProduct.value.condition?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Button(
                    onClick = { /* Handle button click here */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Add to Cart(Per ora non fa nulla)")
                }
                Button(
                    onClick = { /* Handle button click here */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ){
                    Text(text = "Buy(Per ora non fa nulla)")
                }
        }
    }
    /*
    Divider()
    Text(text = searchedProduct.value.title)
    Divider()
    searchedProduct.value.description?.let { Text(text = it) }
    Divider()
    searchedProduct.value.condition?.let { Text(text = it) }
    Divider()
    Text(text = "${searchedProduct.value.price}")
    Divider()

     */
}

/*
@Preview
@Composable
fun ProductScreenPreview() {
    var searchedProduct = remember {
        mutableStateOf(BasicInsertionDto(1L,"null", Float.MIN_VALUE,null,null,null,null,null,"",BasicInsertionDto.Brand.ADIDAS,BasicInsertionDto.Category.ABBIGLIAMENTO, 2L))
    }
    ProductScreen(searchedProduct)
}
 */