package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.view.config.ImageConfiguration
import com.example.vintedandroid.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProductScreen(searchedProduct: MutableState<BasicInsertionDto>, itemsInCart: MutableList<BasicInsertionDto?>, homeViewModel: HomeViewModel) {

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
                    homeViewModel.insertBasicInsertionDtoOnCartDto(item= searchedProduct.value, itemsInCart= itemsInCart) },
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