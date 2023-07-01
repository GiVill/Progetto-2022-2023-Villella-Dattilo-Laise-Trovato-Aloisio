package com.example.vintedandroid.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MainActivity() {
    VintedAndroidTheme {
        MainScreen()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val isMenuOpen = remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "VinteDroid") },
                navigationIcon = {
                    IconButton(onClick = { isMenuOpen.value = true }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Open search screen */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = true,
                    onClick = { /* Navigate */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text(text = "Navigate") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { /* Feed */ },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Feed") },
                    label = { Text(text = "Feed") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { /* Profile */ },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text(text = "Profile") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { /* Cart */ },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                    label = { Text(text = "Cart") }
                )
            }
        },
        drawerContent = {
            DrawerContent(isMenuOpen)
        },
        drawerGesturesEnabled = false,
        content = {
            ItemList()
        }
    )
}

@Composable
fun DrawerContent(isMenuOpen: MutableState<Boolean>) {
    Column(modifier = Modifier.padding(start = 10.dp, top = 16.dp)) {
        Text(
            text = "Menu",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        MenuItem(icon = Icons.Default.Menu, text = "Categories") {
            isMenuOpen.value = false // Chiudi il menu quando l'elemento viene cliccato
        }
        MenuItem(icon = Icons.Default.Menu, text = "Brand") {
            isMenuOpen.value = false // Chiudi il menu quando l'elemento viene cliccato
        }
        MenuItem(icon = Icons.Default.Settings, text = "Settings") {
            isMenuOpen.value = false // Chiudi il menu quando l'elemento viene cliccato
        }
    }
}

@Composable
fun MenuItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = text)
        }
    }
}

@Composable
fun ItemList() {
    val items = remember {
        listOf(
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
            ItemCard(item)
        }
    }
}

@Composable
fun ItemCard(item: Item) {
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
            Button(onClick = { /* Add to cart */ }) {
                Text(text = "Add to Cart")
            }
        }
    }
}

data class Item(val name: String, val description: String, val price: Float)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VintedAndroidTheme {
        MainScreen()
    }
}
