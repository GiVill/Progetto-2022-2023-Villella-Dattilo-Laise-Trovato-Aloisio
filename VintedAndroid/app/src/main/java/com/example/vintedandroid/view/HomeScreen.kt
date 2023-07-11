package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.vintedandroid.Item

import com.google.android.material
.snackbar
.Snackbar;
import androidx.coordinatorlayout
.widget.CoordinatorLayout;
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vintedandroid.client.apis.InsertionApi
import com.example.vintedandroid.client.models.BasicInsertionDto

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(itemsInCart: MutableList<BasicInsertionDto?>, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>) {
/*
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(items) { item ->
            // Replace this with your item composable
            // that represents each item in the list
            Text(text = item)
        }
    }
 */

    //val snackbar: Snackbar = Snackbar.make(containerLayout, "", Snackbar.LENGTH_LONG)

    val scrollState = rememberLazyListState()

    val scrollState1 = rememberLazyListState()

    val scrollState2 = rememberLazyListState()

    val scrollState3 = rememberLazyListState()





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

    val itemsWomen = InsertionApi().getByCategory("DONNA", 0)
    val itemsMan = InsertionApi().getByCategory("UOMO", 0)
    val itemsBaby = InsertionApi().getByCategory("BAMBINI", 0)
    val allItems = InsertionApi().all4(0)




    /*
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            items.forEach { item ->
                ItemCart(item, itemsInCart)
            }
        }


     */

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                // Header item here
                // Add any Composable you want to use as the header
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable(onClick = { /* Open item details activity */ }),
                    elevation = 4.dp
                ) {
                    Text(text = "WOMEN")
                    LazyRow(modifier = Modifier.fillMaxWidth(), state = scrollState1) {
                        items(itemsWomen.results) { item ->
                            ItemCart(item, itemsInCart, navController, searchedProduct)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable(onClick = { /* Open item details activity */ }),
                    elevation = 4.dp
                ) {
                    Text(text = "MAN", fontSize = 20.sp, textAlign = TextAlign.Center)
                    LazyRow(modifier = Modifier.fillMaxWidth(), state = scrollState2) {
                        items(itemsMan.results) { item ->
                            ItemCart(item, itemsInCart, navController, searchedProduct)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable(onClick = { /* Open item details activity */ }),
                    elevation = 4.dp
                ) {
                    Text(text = "BABY")
                    LazyRow(modifier = Modifier.fillMaxWidth(), state = scrollState3) {
                        items(itemsBaby.results) { item ->
                            ItemCart(item, itemsInCart, navController, searchedProduct)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(text = "E molto altro!")
            }
            items(allItems.results) { item ->
                ItemCart(item, itemsInCart, navController, searchedProduct)
            }
        }
    }

    /*
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Woman")

            LazyRow(modifier = Modifier.fillMaxWidth(), state = scrollState1) {
                items(itemsWomen.results) { item ->
                    ItemCart(item, itemsInCart)
                }
            }
            Text(text = "Man")
            LazyRow(modifier = Modifier.fillMaxWidth(), state = scrollState2) {
                items(itemsMan.results) { item ->
                    ItemCart(item, itemsInCart)
                }
            }
        Text(text = "Baby ciuati")

            LazyRow(modifier = Modifier.fillMaxWidth(), state = scrollState3) {
                items(itemsBaby.results) { item ->
                    ItemCart(item, itemsInCart)
                }
            }

    }

     */

    /*
    LazyRow(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        items(itemsInCart.filterNotNull()) { item ->
            ItemCart(item, itemsInCart)

        }
    }
     */

}

@Composable
fun ItemCart(item: BasicInsertionDto, itemsInCart: MutableList<BasicInsertionDto?>, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>) {

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { searchedProduct.value = item ;navController.popBackStack(); navController.navigate("product") }),
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
                    Text("${item.title} added in cart!")
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
        mutableListOf<BasicInsertionDto?>()
    }

    var searchedProduct = remember {
        mutableStateOf(BasicInsertionDto(1L,"null", Float.MIN_VALUE,null,null,null,null,null,"",BasicInsertionDto.Brand.ADIDAS,BasicInsertionDto.Category.ABBIGLIAMENTO, 2L))

    }

    val navController = rememberNavController()


    HomeScreen(itemsInCart, navController, searchedProduct)
}

