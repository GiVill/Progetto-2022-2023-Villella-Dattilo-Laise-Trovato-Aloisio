package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.vintedandroid.client.apis.InsertionApi
import com.example.vintedandroid.client.models.BasicInsertionDto
import com.example.vintedandroid.client.models.PageBasicInsertionDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.application_status.internetChecker
import com.example.vintedandroid.model.dto.CartDto
import com.example.vintedandroid.view.config.ImageConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(itemsInCart: MutableList<BasicInsertionDto?>, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>, application: Context) {

    val scrollState1 = rememberLazyListState()
    val scrollState2 = rememberLazyListState()

    var itemsWomen by remember { mutableStateOf(PageBasicInsertionDto()) }
    var itemsMan by remember { mutableStateOf(PageBasicInsertionDto()) }
    var allItems by remember { mutableStateOf(PageBasicInsertionDto()) }

    var isLoaded by remember { mutableStateOf(false) }
    var isLoaded1 by remember { mutableStateOf(false) }
    var isLoaded2 by remember { mutableStateOf(false) }

    var insertionApi = InsertionApi()

    CoroutineScope(Dispatchers.IO).launch {
        Log.i("home", "Ciao")
        val women = withContext(Dispatchers.IO) {

            insertionApi.getByCategory("DONNA", 0)

        }
        Log.i("home", "Ciao")
        if(women.empty != true) {
            itemsWomen = women
        }
        isLoaded = true
    }
    CoroutineScope(Dispatchers.IO).launch {
        Log.i("home", "Ciao2")
        val man = withContext(Dispatchers.IO) {
            insertionApi.getByCategory("UOMO", 0)
        }
        Log.i("home", "Ciao2")
        if(man.empty != true) {
            itemsMan = man
        }
        isLoaded1 = true
    }
    CoroutineScope(Dispatchers.IO).launch {
        Log.i("home", "Ciao3")
        val items = withContext(Dispatchers.IO) {
            insertionApi.all4(2)
        }
        Log.i("home", "Ciao3")
        if(items.empty != true) {
            allItems = items
        }
            isLoaded2 = true
    }

    if (internetChecker(application)) {

        Box(modifier = Modifier.fillMaxSize()) {

            if (isLoaded && isLoaded1 && isLoaded2) {

                //Dentro la LazyColumn non si possono mettere immagini a quanto pare, fuori si
                ImageConfiguration(imageName = allItems.results[0].imageName, imageScale = ContentScale.Crop)

                //if(itemsWomen.empty != true && itemsMan.empty != true && allItems.empty != true) {

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                /*
                item {
                    // Header item here
                    // Add any Composable you want to use as the header
                    Box(modifier = Modifier.fillMaxWidth()){
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable(onClick = { /* Open item details activity */ }),
                        elevation = 4.dp
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "WOMEN", fontSize = 20.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyRow(modifier = Modifier.fillMaxWidth(), state = scrollState1) {

                            items(itemsWomen.results) { item ->
                                ItemCart(item, itemsInCart, navController, searchedProduct, application)
                            }

                        }
                    }
                    }
                }
                */
                        /*
                item {
                    Box(modifier = Modifier.fillMaxWidth()){

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable(onClick = { /* Open item details activity */ }),
                        elevation = 4.dp
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "MAN", fontSize = 20.sp, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))

                        LazyRow(modifier = Modifier.fillMaxWidth(), state = scrollState2) {

                        items(itemsMan.results) { item ->
                            ItemCart(item, itemsInCart, navController, searchedProduct, application)
                        }

                        }
                    }
                    }
                }
                 */

                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(6.dp)
                            ) {
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Articoli: ",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                ) //text = "E molto altro!"
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                        items(allItems.results) { item ->
                            ItemCart(item, itemsInCart, navController, searchedProduct, application)
                        }

                    }
                //} else{ Text(text="Error while connecting to the server") }
        } else{ CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }

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
        Text(text = "Baby ")

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
    } else { noConnectionScreen(application = application)  }
}

@Composable
fun ItemCart(item: BasicInsertionDto, itemsInCart: MutableList<BasicInsertionDto?>, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>, application: Context ) {

    var showDialog by remember { mutableStateOf(false) }

    //Box(modifier = Modifier.fillMaxWidth()){
        ImageConfiguration(imageName = item.imageName, imageScale = ContentScale.Crop)
    //}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {
                searchedProduct.value = item;navController.popBackStack(); navController.navigate(ScreenController.Product.route)
            }),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
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
                    CoroutineScope(Dispatchers.IO).launch {
                        AppDatabase.getInstance(context = application.applicationContext).cartDao().insert(converter(item))
                    }
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

//TODO Andrebbe sostituita con la classe apposita!
/*
@SuppressLint("SuspiciousIndentation")
@Composable
fun displayImage(allItems: PageBasicInsertionDto) {

    Box(modifier = Modifier.fillMaxWidth()) {


    val url = "https://192.168.1.90:8010/vintedProject-api/v1/images/file_472864ab-51c9-4ff6-bab2-85a8871eb446.jpg"//${allItems.results[2].imageName}"
        Log.i("tag", "ok, no? => $url")
        val painter: ImagePainter = rememberImagePainter(url)//: String? = null


        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painter,
                contentDescription = null, // Provide a proper content description
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )}}
}
 */

fun converter(item: BasicInsertionDto): CartDto {

    return CartDto(
        title = item.title,
        price = item.price,
        description = item.description,
        condition = item.condition,
        //creationDate = null,
        //creationDate = item.creationDate as LocalDate?,
        isPrivate = item.isPrivate,
        //endDate = null,
        //endDate = item.endDate as LocalDate?,
        imageName = item.imageName,
        brand = item.brand,
        category = item.category,
        userId = item.userId
    )

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

/*
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {

    val itemsInCart = remember { mutableListOf<BasicInsertionDto?>() }

    var searchedProduct = remember {
        mutableStateOf(BasicInsertionDto(1L,"null", Float.MIN_VALUE,null,null,null,null,null,"",BasicInsertionDto.Brand.ADIDAS,BasicInsertionDto.Category.ABBIGLIAMENTO, 2L))

    }

    val navController = rememberNavController()

    //HomeScreen(itemsInCart, navController, searchedProduct, )
}
 */
