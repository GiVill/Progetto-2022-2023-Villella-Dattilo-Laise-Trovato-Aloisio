package com.example.vintedandroid.view

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.vintedandroid.R
import com.example.vintedandroid.model.application_status.internetChecker
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.view.config.PersonalizedAsyncImage
import com.example.vintedandroid.viewmodel.HomeViewModel
import com.example.vintedandroid.viewmodel.MyInsertionViewModel

@Composable
fun MyInsertionActivity(application: Context, searchedProduct: MutableState<BasicInsertionDto>, myInsertionViewModel: MyInsertionViewModel, navController: NavHostController) {
    var page = 0
    var pageInsertion by remember { mutableStateOf(myInsertionViewModel.getMyInsertion(page)) }
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
                    ItemCart(searchedProduct,item, navController = navController)
                }
                item {
                    Row (modifier = Modifier.fillMaxSize()){
                        Button(
                            onClick = {
                                if (page > 0) {
                                    //isLoaded = false
                                    page -= 1
                                    pageInsertion = myInsertionViewModel.getMyInsertion(page)
                                }
                            },
                            enabled = page > 0
                        ) {
                            Text(text = stringResource(R.string.previous_page))
                        }
                        Button(
                            onClick = {
                                if(page <= pageInsertion.totalPages!!) {
                                    //isLoaded = false
                                    page += 1
                                    pageInsertion = myInsertionViewModel.getMyInsertion(page)
                                }
                            },
                            enabled = pageInsertion.totalPages!! >= page
                        ) { Text(text = stringResource(R.string.next_page)) }
                    }
                }
            }
            //} else{ CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
        }
    } else { noConnectionActivity(application = application)  }
}

@Composable
fun ItemCart(searchedProduct: MutableState<BasicInsertionDto>,item: BasicInsertionDto, navController: NavHostController) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                //TODO Da aggiustare
                searchedProduct.value = item
                navController.navigate(ScreenController.Product.route)
            },
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

        }
    }
}
