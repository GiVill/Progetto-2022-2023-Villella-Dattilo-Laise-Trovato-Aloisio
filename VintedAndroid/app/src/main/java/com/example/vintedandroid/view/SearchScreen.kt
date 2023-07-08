package com.example.vintedandroid.view
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
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.client.apis.InsertionApi
import com.example.vintedandroid.client.models.BasicInsertionDto
import com.example.vintedandroid.client.models.PageBasicInsertionDto
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(searchText: String, insertionApi: InsertionApi) {
    var searchResults by remember { mutableStateOf(PageBasicInsertionDto()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(searchText) {
        if (searchText.isNotEmpty()) {
            coroutineScope.launch {
                searchResults = insertionApi.getByTitle(searchText, 0)
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(searchResults.results) { result ->
            SearchResultCard(result = result)
        }
    }
}

@Composable
fun SearchResultCard(result: BasicInsertionDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = result.title, style = MaterialTheme.typography.h6)
            result.description?.let { Text(text = it, style = MaterialTheme.typography.body1) }
            Text(text = "Prezzo: ${result.price}", style = MaterialTheme.typography.body2)
        }
    }
}
