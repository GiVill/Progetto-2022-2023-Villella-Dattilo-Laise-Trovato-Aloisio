package com.example.vintedandroid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vintedandroid.theme.VintedAndroidTheme
import com.example.vintedandroid.view.LoginScreen
import com.example.vintedandroid.view.ScreenController
import com.example.vintedandroid.view.SearchResultCard
import com.example.vintedandroid.view.SetupNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.nio.ByteBuffer
import android.Manifest
import com.example.vintedandroid.model.application_status.internetChecker
import com.example.vintedandroid.view.noConnectionScreen
import com.example.vintedandroid.viewmodel.CartViewModel
import com.example.vintedandroid.viewmodel.HomeViewModel
import com.example.vintedandroid.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VintedAndroidTheme {
                StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitNetwork().build())

                if (internetChecker(application.applicationContext)) {
                    //Toast.makeText(this, "Connected to internet", Toast.LENGTH_SHORT).show()

                    val navController = rememberNavController()

                    val searchText = remember { mutableStateOf("") }

                    var controlloSuToken = true //questa variabile servirebbe per capire se all'avvio dell'app hai fatto il login oppure no. Per ora non fa nessun controllo

                    if(controlloSuToken) {

                        Scaffold(
                            topBar = { ApplicationTopBar(searchText, navController) },
                            bottomBar = { ApplicationBottomBar(navController) },
                            content = {
                            Box(modifier = Modifier.padding(it)) {
                                SetupNavGraph(
                                    navController = navController,
                                    searchText = searchText,
                                    application = application.applicationContext,
                                    homeViewModel =  HomeViewModel(application),
                                    userViewModel = UserViewModel(application),
                                    cartViewModel = CartViewModel(application)
                                )
                            }
                        } )
                    }
                    else{
                        navController.navigate(ScreenController.Login.route)
                        //LoginScreen(navController = navController, application) //Non funziona bene
                    }
                } else { noConnectionScreen(application = application) }
            }
        }
    }
}

@Composable
fun ApplicationTopBar(searchText: MutableState<String>, navController: NavHostController) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val topBarHandler by remember(currentBackStackEntry) { derivedStateOf{navController.currentBackStackEntry?.destination?.route == ScreenController.Search.route} }

    //var text by remember { mutableStateOf("") }
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

     */


    TopAppBar(
        title = {
            if(!topBarHandler){
                Text("Vinted",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        actions = {
            if(topBarHandler) {
                //navController.popBackStack(); navController.navigate("search")

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
        }
    )

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
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VintedAndroidTheme {
        //MainScreen()
    }
}
