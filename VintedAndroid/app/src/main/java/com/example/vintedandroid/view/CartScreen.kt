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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.Item
import com.example.vintedandroid.client.models.BasicInsertionDto
import com.example.vintedandroid.model.AppDatabase
import android.content.Context
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.vintedandroid.client.models.PageBasicInsertionDto
import com.example.vintedandroid.model.dto.CartDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty

//import com.google.android.material.snackbar.Snackbar

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CartScreen(itemsInCart: MutableList<BasicInsertionDto?>, application: Context) {

    //var itemsFromDB: List<CartDto>? = null
    //var itemsFromDB by remember { mutableStateOf<List<CartDto>>(emptyList()) }
    var itemsFromDB = remember { mutableStateListOf<CartDto>() }
    /*
    var itemsFromDB by remember {
        mutableStateOf(PageBasicInsertionDto())
    }
     */

    var isLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (itemsFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).cartDao().getAll()
            }
            //itemsFromDB.clear()
            itemsFromDB.addAll(databaseItems)
            isLoaded = true
        }
    }
    //TODO BasicInsertionDto

    //val snackbar = Snackbar.make( "This is a simple popup", Snackbar.LENGTH_SHORT)

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (isLoaded){
        if(itemsFromDB.isNotEmpty() == true){
            itemsFromDB!!.forEach { item ->
                ItemsInCart(item, itemsFromDB, application, itemsFromDB)
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
                    Text(text = "No items in cart!")
                }
            }
        }
        }else{
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun ItemsInCart(item : CartDto, itemsInCart: List<CartDto>?, application: Context, itemsFromDB: MutableList<CartDto>) {
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
                //itemsInCart.remove(item)
                CoroutineScope(Dispatchers.IO).launch {
                    itemsFromDB.remove(item)
                    AppDatabase.getInstance(context = application.applicationContext).cartDao().delete(item)
                }
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

    //CartScreen(itemsInCart)
}