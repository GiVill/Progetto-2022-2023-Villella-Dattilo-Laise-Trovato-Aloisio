package com.example.vintedandroid.view
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vintedandroid.client.apis.InsertionApi
import com.example.vintedandroid.client.models.BasicInsertionDto
import com.example.vintedandroid.client.models.PageBasicInsertionDto
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(searchText: MutableState<String>, insertionApi: InsertionApi, navController: NavHostController, searchedProduct: MutableState<BasicInsertionDto>) {

    var searchResults by remember { mutableStateOf(PageBasicInsertionDto()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(searchText.value) {

        if (searchText.value != "") {

            coroutineScope.launch {
                Log.i("tag", "You have typed this text: ${searchText.value}")
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





    /*

    var active by remember { mutableStateOf(false) }
    var searchHistory = remember{ mutableStateListOf("ciao", "ciao2") }

    var searchResults by remember { mutableStateOf(PageBasicInsertionDto()) }
    val coroutineScope = rememberCoroutineScope()
    val insertionApi = InsertionApi()
    var searchedProduct = remember {
        mutableStateOf(
            BasicInsertionDto(1L,"null", Float.MIN_VALUE,null,null,null,null,null,"",
                BasicInsertionDto.Brand.ADIDAS,
                BasicInsertionDto.Category.ABBIGLIAMENTO, 2L)
        )

    }


    SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = searchText.value,
                onQueryChange = {
                    searchText.value = it
                },
                onSearch = {
                    searchHistory.add(searchText.value)
                    active = false
                },
                active = active,
                onActiveChange ={
                    active = it
                },
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    if(active){
                        Icon(
                            modifier = Modifier.clickable{
                                if(searchText.value.isNotEmpty()){
                                    searchText.value = ""
                                }else{
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon")
                    }
                },
                colors = SearchBarDefaults.colors(Color.Gray) // Il colore non andrebbe impostato qui
            ) {
                searchHistory.forEach{
                    Row(modifier = Modifier.padding(all = 14.dp)){
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            imageVector = Icons.Default.History,
                            contentDescription = "Hystory Icon"
                        )
                        Text(text = it)
                    }
                }



                LaunchedEffect(searchText.value) {

                    if (searchText.value != "") {

                        coroutineScope.launch {
                            Log.i("tag", "Hai inserito questo testo => ${searchText.value}")
                            searchResults = insertionApi.getByTitle(searchText.value, 0)
                            Log.i("tag", searchResults.toString());
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(searchResults.results) { result ->
                        //Text(text = "test e basta")
                        SearchResultCard(result = result, navController, searchedProduct)
                    }
                }



            }

     */


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
                searchedProduct.value = result;navController.popBackStack(); navController.navigate(ScreenController.Product.route)
            }),
        ) {
            Text(text = result.title, style = MaterialTheme.typography.h6)
            result.description?.let { Text(text = it, style = MaterialTheme.typography.body1) }
            Text(text = "Prezzo: ${result.price}", style = MaterialTheme.typography.body2)
        }
    }
}
