package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
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

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProductScreen(searchedProduct: MutableState<BasicInsertionDto>, application: Context, itemsInCart: MutableList<BasicInsertionDto?>) {

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

            //ImageConfiguration(imageName = searchedProduct.value.imageName, imageScale = ContentScale.Fit)

            searchedProductDisplayInfo(searchedProduct = searchedProduct)
            Button(
                onClick = {
                    if (!itemsInCart.contains(searchedProduct.value)) {
                        itemsInCart.add(searchedProduct.value)
                        CoroutineScope(Dispatchers.IO).launch {
                            converter(searchedProduct.value)?.let {
                                AppDatabase.getInstance(context = application.applicationContext).cartDao().insert(
                                    it
                                )
                            }
                        }
                        Log.i("ProductScreen::class", "Item added!")
                        itemsInCart.forEachIndexed { index, item ->
                            Log.i("ProductScreen::class", "Item $index: $item") //stampa tutto il carrello
                        }
                    } else {
                        Log.i("ProductScreen::class", "Cannot add the item because is already in the cart!")
                        itemsInCart.forEachIndexed { index, item ->
                            Log.i("ProductScreen::class", "Item $index: $item") //stampa tutto il carrello
                        }
                    }
                          },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Add to Cart")
            }
            Button(
                onClick = { /* Handle button click here */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ){
                Text(text = "Buy(Per ora non fa nulla)")
            }
        }
    }
}


@Composable
private fun searchedProductDisplayInfo(searchedProduct: MutableState<BasicInsertionDto>){

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