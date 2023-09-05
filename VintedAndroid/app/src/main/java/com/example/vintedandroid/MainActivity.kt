package com.example.vintedandroid

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.theme.VintedAndroidTheme
import com.example.vintedandroid.view.ScreenController
import com.example.vintedandroid.view.SetupNavGraph
import com.example.vintedandroid.model.application_status.internetChecker
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.view.noConnectionActivity
import com.example.vintedandroid.viewmodel.AddViewModel
import com.example.vintedandroid.viewmodel.CartViewModel
import com.example.vintedandroid.viewmodel.ChatViewModel
import com.example.vintedandroid.viewmodel.HomeViewModel
import com.example.vintedandroid.viewmodel.LoginRegistrationViewModel
import com.example.vintedandroid.viewmodel.MyInsertionViewModel
import com.example.vintedandroid.viewmodel.OfferViewModel
import com.example.vintedandroid.viewmodel.OrderViewModel
import com.example.vintedandroid.viewmodel.ProductViewModel
import com.example.vintedandroid.viewmodel.UpdatePasswordViewModel
import com.example.vintedandroid.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                                cartViewModel = CartViewModel(application),
                                loginRegistrationViewModel = LoginRegistrationViewModel(application),
                                updatePasswordViewModel = UpdatePasswordViewModel(application),
                                offerViewModel = OfferViewModel(application),
                                productViewModel = ProductViewModel(application,),
                                chatViewModel = ChatViewModel(application, userViewModel = UserViewModel(application = application)),
                                orderViewModel = OrderViewModel(application),
                                addViewModel = AddViewModel(application),
                                myInsertionViewModel = MyInsertionViewModel(application)
                            )
                            getToken(application) { logged ->
                                if (logged != null) {
                                    //TODO Refresh Token
                                    LoggedUserDetails.getInstance().setCurrentUser(logged)
                                    Log.i("MainActivity::class", "${LoggedUserDetails.getInstance().getCurrentUser()}")
                                }
                                else{
                                    Log.i("MainActivity::class", "No User in DB")
                                    //navController.navigate(ScreenController.Login.route)
                                }
                            }
                        }
                    } )
                } else { noConnectionActivity(application = application) }
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
            if(!topBarHandler) {
                Row(Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stringResource(R.string.vinted))
                    }
                    Column {
                        Icon(
                            imageVector = Icons.Default.Message,
                            contentDescription = stringResource(R.string.message),
                            modifier = Modifier.clickable { navController.navigate(ScreenController.Chat.route) }
                        )
                    }
                }
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

private fun getToken(application: Context, callback: (UserDatabaseDto?) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val loggedUser = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getSingleUser()
        callback(loggedUser)
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VintedAndroidTheme {
        //MainScreen()
    }
}
