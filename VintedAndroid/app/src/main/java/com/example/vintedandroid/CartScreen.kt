package com.example.vintedandroid

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.google.android.material.snackbar.Snackbar

@Composable
fun CartScreen(itemsInCart: MutableList<Item?>) {
    //val snackbar = Snackbar.make( "This is a simple popup", Snackbar.LENGTH_SHORT)

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(itemsInCart.isNotEmpty()) {
            itemsInCart.forEach { item ->
                if (item != null) {
                    Text(text = item.name)
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (item != null) {
                    Text(text = item.description)
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (item != null) {
                    Text(text = "$${item.price}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {

                    //snackbar.show()
                    itemsInCart.remove(item)
                    Log.i("cart", "Item : $item removed from the cart")
                }) {
                    Text(text = "Discard")
                }
            }
        }
        else{
            Text(text = "No items in cart! Dammi li sordi")
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {

    val itemsInCart = remember {
        mutableListOf<Item?>()
    }

    CartScreen(itemsInCart)
}