package com.example.vintedandroid.view
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.PageBasicInsertionDto
import kotlinx.coroutines.launch
import com.example.vintedandroid.R


@Composable
fun SearchActivity(searchText: MutableState<String>, insertionApi: InsertionApi, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>) {

    var searchResults by remember { mutableStateOf(PageBasicInsertionDto()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(searchText.value) {

        if (searchText.value != "") {
            coroutineScope.launch {
                searchResults = insertionApi.getByTitle(searchText.value, 0)
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(searchResults.results) { result ->
            SearchResultCard(result, navController, searchedProduct)
        }
    }
}

@Composable
fun SearchResultCard(result: BasicInsertionDto, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        elevation = 4.dp
    ) {
        Column(modifier =
        Modifier
            .padding(16.dp)
            .clickable(onClick = {
                searchedProduct.value = result;navController.popBackStack(); navController.navigate(
                ScreenController.Product.route
            )
            }),
        ) {
            Text(text = result.title, style = MaterialTheme.typography.h6)
            result.description?.let { Text(text = it, style = MaterialTheme.typography.body1) }
            Text(text = stringResource(R.string.price)+": ${result.price}", style = MaterialTheme.typography.body2)
        }
    }
}
