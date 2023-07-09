package com.example.vintedandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vintedandroid.client.models.UserDatabaseDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.theme.VintedAndroidTheme
import com.example.vintedandroid.view.SetupNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VintedAndroidTheme {
                StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitNetwork().build())

                /*
                //QUESTE FUNZIONANO SOLO A LUIGI! AGLI ALTRI DARà ERRORE!!
                val userApi = UserApi()
                val users = userApi.all()
                users.forEach { user ->
                    Log.i("papà",user.toString())
                }*/

                //var c = AppDatabase.getInstance(context = application.applicationContext).userDao().getAll()



                val navController = rememberNavController()

                val searchText = remember { mutableStateOf("") }

                //SetupNavGraph(navController = navController)

                Scaffold(
                    topBar = { ApplicationTopBar(searchText, navController) },
                    bottomBar = { ApplicationBottomBar(navController) }) {
                    Box(modifier = Modifier.padding(it)) {


                        CoroutineScope(Dispatchers.IO).launch {
                            //val user1 = UserDatabaseDto(UUID.randomUUID().toString(), "ciao", "Boh", "ciaoBoh")
                            //AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().insert(user1) //Inserimento di un utente
                            //var c = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll() //Get di un utente
                            //Log.i("tag", c.toString())
                        }




                        SetupNavGraph(navController = navController, searchText = searchText, application = application.applicationContext)
                    }

                }
            }
        }
    }

}

@Composable
fun ApplicationTopBar(searchText: MutableState<String>, navHostController: NavHostController) {

    TopAppBar(
        title = { },
        actions = {
            TextField(
                value = searchText.value,
                onValueChange = { searchText.value = it; },
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth(1f),
                singleLine = true,
                placeholder = { Icon(Icons.Default.Search, contentDescription = "Search")}
            )
        }
    )

    /*
    TopBar fatta da Umberto

    TopAppBar(
        title = { Text(text = "VinteDroid") },
        navigationIcon = {
            IconButton(onClick = { true }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /* Open search screen */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
    )

    */

    /*

    TopBar del professore

    //val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    //val showBackIcon by remember(currentBackStackEntry) { derivedStateOf { navHostController.previousBackStackEntry != null } }


    TopAppBar(title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            }
        },
        actions = {
            androidx.compose.material3.IconButton(onClick = {}) {
                androidx.compose.material3.Icon(
                    Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.app_name)
                )
            }
        }
    )

     */
}


@Composable
fun ApplicationBottomBar(navController: NavHostController) {//,selectedIndex: MutableState<Int>

    // TUTTI i navController.popBackStack(); ANDREBBERO gestiti diversamente, per ora sono ni

    BottomAppBar {

        BottomNavigation {
            BottomNavigationItem(
                selected = true,
                onClick = { navController.popBackStack(); navController.navigate("home") },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text(text = "Navigate") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.popBackStack(); navController.navigate("search") }, //navController.navigate("search")
                icon = { Icon(Icons.Default.Search, contentDescription = "search") },
                label = { Text(text = "Search") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.popBackStack(); navController.navigate("add") }, //navController.navigate("add")
                icon = { Icon(Icons.Default.AddCircle, contentDescription = "add_circle") },
                label = { Text(text = "Add") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.popBackStack(); navController.navigate("bottom-bar-profile") }, //navController.navigate("profile")
                icon = { Icon(Icons.Default.AccountCircle, contentDescription = "add_circle") },
                label = { Text(text = "Profile") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.popBackStack(); navController.navigate("cart") },
                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                label = { Text(text = "Cart") }
            )
        }
        /*
        NavigationBar {
            NavigationBarItem(
                selected = selectedIndex.value == 0,
                onClick = { selectedIndex.value = 0 },
                icon = {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            )
            NavigationBarItem(
                selected = selectedIndex.value == 1,
                onClick = { selectedIndex.value = 1 },
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            )
        }
    }

         */
    }
}

data class Item(val name: String, val description: String, val price: Float)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VintedAndroidTheme {
        //MainScreen()
    }
}
