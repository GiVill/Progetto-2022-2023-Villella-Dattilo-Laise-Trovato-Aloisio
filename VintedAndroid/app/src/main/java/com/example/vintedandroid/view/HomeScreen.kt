package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.application_status.internetChecker
import com.example.vintedandroid.model.dto.CartDto
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.PageBasicInsertionDto
import com.example.vintedandroid.view.config.ImageConfiguration
import com.example.vintedandroid.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var page: Int = 0

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "StateFlowValueCalledInComposition"
)
@Composable
fun HomeScreen(itemsInCart: MutableList<BasicInsertionDto?>, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>, application: Context, viewModel: HomeViewModel) {


    var pageInsertion by remember { mutableStateOf(viewModel.getAllInsertion(page)) }
    var allInsertion = mutableListOf<BasicInsertionDto>()

    /*
    var allInsertion by remember { mutableStateOf(viewModel.getAllInsertion(page)) }
    var pageInsertion by remember(allInsertion) {
    derivedStateOf {
        // Calculate pageInsertion based on allInsertion whenever allInsertion changes
        // You can put your logic here to calculate pageInsertion
        // For example, if you want pageInsertion to be a sublist of allInsertion:
        allInsertion.subList(0, minOf(page, allInsertion.size))
    }
}
     */

    //    val itemsInCart = remember { mutableStateListOf<BasicInsertionDto?>() }

    if (internetChecker(application)) {
        Box(modifier = Modifier.fillMaxSize()) {

            //if (isLoaded1 && isLoaded2) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                items(allInsertion) { item ->
                    ItemCart(item, itemsInCart, navController, searchedProduct, viewModel)
                }
                item {
                    Row (modifier = Modifier.fillMaxSize()){
                        Button(
                            onClick = {
                                page -= 1
                                pageInsertion = viewModel.getAllInsertion(page)
                            },
                            enabled = page > 0
                        ) {
                            Text(text = "Previous Page")
                        }
                        Button(
                            onClick = {
                                page += 1
                                pageInsertion = viewModel.getAllInsertion(page)
                            },
                            enabled = pageInsertion.totalPages != page
                        ) { Text(text = "Next Page") }
                    }
                }
            }

                //} else{ Text(text="Error while connecting to the server") }
        //} else{ CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
        }
    } else { noConnectionScreen(application = application)  }
}

@Composable
fun ItemCart(item: BasicInsertionDto, itemsInCart: MutableList<BasicInsertionDto?>, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>, viewModel: HomeViewModel ) {

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {
                searchedProduct.value = item;navController.popBackStack(); navController.navigate(
                ScreenController.Product.route
            )
            }),
        elevation = 4.dp
    ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
                //horizontalAlignment = Alignment.CenterHorizontally
                ) {
                AsyncImage(
                    model = "https://192.168.1.90:8010/vintedProject-api/v1/images/file_bf52168a-261d-469e-a1a4-55ba0da6fdac.jpg",
                    contentDescription = "Async Image Of The Product"
                )
                Divider()

                Text(text = item.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.description)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$${item.price}")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    showDialog = true
                    viewModel.insertBasicInsertionDtoOnCartDto(item = item, itemsInCart = itemsInCart)
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
