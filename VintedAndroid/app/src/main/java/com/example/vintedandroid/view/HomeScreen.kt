package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.vintedandroid.Item

import com.google.android.material
.snackbar
.Snackbar;
import androidx.coordinatorlayout
.widget.CoordinatorLayout;

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(itemsInCart: MutableList<Item?>) {


    //val snackbar: Snackbar = Snackbar.make(containerLayout, "", Snackbar.LENGTH_LONG)

    val items = remember {
        listOf(
            //This is an immutable list!
            Item("Item 1", "Description 1", 10.99f),
            Item("Item 2", "Description 2", 15.99f),
            Item("Item 3", "Description 3", 20.99f),
            Item("Item 4", "Description 1", 10.99f),
            Item("Item 5", "Description 2", 15.99f),
            Item("Item 6", "Description 3", 20.99f),
            // ...
        )
    }



    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        items.forEach { item ->
            ItemCart(item, itemsInCart)
        }
    }

}

@Composable
fun ItemCart(item: Item, itemsInCart: MutableList<Item?>) {

    var showDialog by remember { mutableStateOf(false) }

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
            Text(text = item.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$${item.price}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                showDialog = true;

                if (!itemsInCart.contains(item)) {
                    itemsInCart.add(item)
                    Log.i("cart", "Item added!")
                    itemsInCart.forEachIndexed { index, item ->
                        Log.i("cart", "Item $index: $item") //stampa tutto il carrello
                    }
                } else {
                    Log.i("cart", "Cannot add the item because is already in the cart!")
                    itemsInCart.forEachIndexed { index, item ->
                        Log.i("cart", "Item $index: $item") //stampa tutto il carrello
                    }
                }
            }) {
                Text(text = "Add to Cart")
            }
            if (showDialog) {
                PopupDialog(onDismiss = { showDialog = false }) {
                    Text("${item.name} added in cart!  Mannaggia ai Negri!")
                }
            }
        }
    }
}

@Composable
fun PopupDialog(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            content()
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {

    val itemsInCart = remember {
        mutableListOf<Item?>()
    }

    HomeScreen(itemsInCart)
}

