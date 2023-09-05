package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.vintedandroid.R
import com.example.vintedandroid.model.application_status.internetChecker
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.view.config.PersonalizedAsyncImage
import com.example.vintedandroid.viewmodel.HomeViewModel

var page: Int = 0

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "StateFlowValueCalledInComposition"
)
@Composable
fun HomeActivity(itemsInCart: MutableList<BasicInsertionDto?>, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>, application: Context, viewModel: HomeViewModel) {


    var pageInsertion by remember { mutableStateOf(viewModel.getAllInsertion(page)) }
    var allInsertion = mutableListOf<BasicInsertionDto>()
    //var isLoaded by remember { mutableStateOf(false) }

    //LaunchedEffect(pageInsertion) { isLoaded = true }

    if (internetChecker(application)) {
        Box(modifier = Modifier.fillMaxSize()) {

            //if (isLoaded) {
                allInsertion.addAll(pageInsertion.results)
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.article),
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
                                    if (page > 0) {
                                        //isLoaded = false
                                        page -= 1
                                        pageInsertion = viewModel.getAllInsertion(page)
                                    }
                                },
                                enabled = page > 0
                            ) {
                                Text(text = stringResource(R.string.previous_page))
                            }
                            Button(
                                onClick = {
                                    if(page < pageInsertion.totalPages!!) {
                                        //isLoaded = false
                                        page += 1
                                        pageInsertion = viewModel.getAllInsertion(page)
                                    }
                                },
                                enabled = pageInsertion.totalPages != page
                            ) { Text(text = stringResource(R.string.next_page)) }
                        }
                    }
                }
            //} else{ CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
        }
    } else { noConnectionActivity(application = application)  }
}

@Composable
fun ItemCart(item: BasicInsertionDto, itemsInCart: MutableList<BasicInsertionDto?>, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>, viewModel: HomeViewModel ) {

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {
                searchedProduct.value = item
                navController.navigate(ScreenController.Product.route)
            }),
        elevation = 4.dp
    ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
                //horizontalAlignment = Alignment.CenterHorizontally
                ) {

                PersonalizedAsyncImage(imageName = item.imageName, subject = "HomeActivity::class")

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
                    Text(text = stringResource(R.string.add_cart))
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
