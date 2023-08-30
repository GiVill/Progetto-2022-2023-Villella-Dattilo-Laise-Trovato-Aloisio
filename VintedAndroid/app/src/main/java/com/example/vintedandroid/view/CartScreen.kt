package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.vintedandroid.model.dto.CartDto
import com.example.vintedandroid.viewmodel.CartViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CartScreen(cartViewModel: CartViewModel) {

    //var isLoaded by remember { mutableStateOf(false) }

    val cartItemsState: MutableList<CartDto> by cartViewModel.getAll().collectAsState(initial = mutableListOf())

    ListOfItem(cartItemsState = cartItemsState, cartViewModel = cartViewModel)
}

@Composable
fun ListOfItem(cartItemsState: MutableList<CartDto>, cartViewModel: CartViewModel) {
    //if(isLoaded){
    if (cartItemsState.isNotEmpty()) {
        LazyColumn {
            items(cartItemsState) { cartItem ->
                    Log.i("Cart", "item in cart is: $cartItem")
                    ItemsInCart(
                        item = cartItem,
                        cartViewModel = cartViewModel
                    )
                }
            }
        } else {
            NoItemsInCart()
        }
    //}else{
    //    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
    //}

}

@Composable
fun ItemsInCart(item : CartDto, cartViewModel: CartViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { /* Open item details activity */ }),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = item.title)
            Spacer(modifier = Modifier.height(8.dp))
            item.description?.let { Text(text = it) }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$${item.price}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { cartViewModel.removeItemsInCartFromDatabase(item) })

            { Text(text = "Discard") }
        }
    }
}

@Composable
fun NoItemsInCart() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { /* Open item details activity */ }),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        { Text(text = "No items in cart!") }
    }

}