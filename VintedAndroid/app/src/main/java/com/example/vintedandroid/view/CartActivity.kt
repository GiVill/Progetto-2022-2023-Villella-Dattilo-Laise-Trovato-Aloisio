package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.vintedandroid.R
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.model.dto.CartDto
import com.example.vintedandroid.swagger.client.models.Order
import com.example.vintedandroid.swagger.client.models.OrderDto
import com.example.vintedandroid.viewmodel.CartViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CartActivity(cartViewModel: CartViewModel) {

    //var isLoaded by remember { mutableStateOf(false) }

    val cartItemsState: MutableList<CartDto> by cartViewModel.getAll().collectAsState(initial = mutableListOf())

    ListOfItem(cartItemsState = cartItemsState, cartViewModel = cartViewModel)
}

@Composable
fun ListOfItem(cartItemsState: MutableList<CartDto>, cartViewModel: CartViewModel) {
    //if(isLoaded){
    if (cartItemsState.isNotEmpty()) {
        var totalCost : Float = 0f
        var insertionIdList: MutableList<Long> = mutableListOf()
        LazyColumn {
            items(cartItemsState) { cartItem ->
                    Log.i("Cart", "item in cart is: $cartItem")
                totalCost += cartItem.price
                cartItem.id?.let { insertionIdList.add(it) }
                    ItemsInCart(
                        item = cartItem,
                        cartViewModel = cartViewModel
                    )
                }
            item {
                Button(onClick = {
                    var newOrder = LoggedUserDetails.getInstance().getCurrentUser().id?.let {
                        OrderDto(0,null,"PAYPAL",insertionIdList.toTypedArray(),totalCost,
                            it
                        )
                    }
                    if (newOrder != null) {
                        cartViewModel.createOrder(newOrder)
                    }
                    //Toast.makeText(application.applicationContext, "Offert Sended", Toast.LENGTH_SHORT).show()
                }) {
                    Text(stringResource(R.string.create_order))
                }
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

            { Text(text = stringResource(R.string.discard)) }
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
        { Text(text = stringResource(R.string.no_items_in_cart)) }
    }

}