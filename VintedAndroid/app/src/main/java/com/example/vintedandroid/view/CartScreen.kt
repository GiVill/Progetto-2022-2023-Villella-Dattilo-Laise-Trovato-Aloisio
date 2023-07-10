package com.example.vintedandroid.view

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.Item
import com.example.vintedandroid.client.models.BasicInsertionDto

//import com.google.android.material.snackbar.Snackbar

@Composable
fun CartScreen(itemsInCart: MutableList<BasicInsertionDto?>) {

    //TODO BasicInsertionDto

    //val snackbar = Snackbar.make( "This is a simple popup", Snackbar.LENGTH_SHORT)

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if(itemsInCart.isNotEmpty()){
            itemsInCart.forEach { item ->
                if (item != null) {
                    ItemsInCart(item, itemsInCart)
                }
            }
        }
        else {
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
                    Text(text = "No items in cart! Dammi li sordi")
                }
            }
        }
    }
}

@Composable
fun ItemsInCart(item : BasicInsertionDto, itemsInCart: MutableList<BasicInsertionDto?>) {
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
            Button(onClick = {
                //snackbar.show()
                itemsInCart.remove(item)
                Log.i("cart", "Item : $item removed from the cart")
            })
            {
            Text(text = "Discard")
            }
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {

    val itemsInCart = remember {
        mutableListOf<BasicInsertionDto?>()
    }

    CartScreen(itemsInCart)
}