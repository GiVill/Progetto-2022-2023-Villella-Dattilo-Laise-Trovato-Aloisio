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
import com.example.vintedandroid.viewmodel.ChatMessageViewModel
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

                    val navController = rememberNavController()

                    val searchText = remember { mutableStateOf("") }

                    Scaffold(
                        topBar = { ApplicationTopBar(searchText, navController) },
                        bottomBar = { ApplicationBottomBar(navController) },
                        content = {
                        Box(modifier = Modifier.padding(it)) {

                            val chatId = getToken(application) { logged ->
                                if (logged != null) {
                                    LoggedUserDetails.getInstance().setCurrentUser(logged)
                                    Log.i("MainActivity::class", "${LoggedUserDetails.getInstance().getCurrentUser()}")
                                }
                                else{
                                    Log.i("MainActivity::class", "No User in DB")
                                    //navController.navigate(ScreenController.Login.route)
                                }
                            }

                            SetupNavGraph(
                                navController = navController,
                                searchText = searchText,
                                application = application.applicationContext,
                                homeViewModel =  HomeViewModel(application),
                                userViewModel = UserViewModel(application),
                                cartViewModel = CartViewModel(application),
                                loginRegistrationViewModel = LoginRegistrationViewModel(application),
                                updatePasswordViewModel = UpdatePasswordViewModel(),
                                offerViewModel = OfferViewModel(),
                                productViewModel = ProductViewModel(application,),
                                chatViewModel = ChatViewModel(),
                                orderViewModel = OrderViewModel(),
                                addViewModel = AddViewModel(application),
                                myInsertionViewModel = MyInsertionViewModel(),
                                chatMessageViewModel = ChatMessageViewModel(application),
                            )
                            getToken(application) { logged ->
                                if (logged != null) {
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

}


@Composable
fun ApplicationBottomBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomAppBar {
        BottomNavigation {
            BottomNavigationItem(
                selected = currentRoute == ScreenController.Home.route,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(ScreenController.Home.route)
                },
                icon = { Icon(Icons.Default.Home, contentDescription = ScreenController.Home.route) },
                label = { Text(text = "Home") }
            )
            BottomNavigationItem(
                selected = currentRoute == ScreenController.Search.route,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(ScreenController.Search.route)
                },
                icon = { Icon(Icons.Default.Search, contentDescription = ScreenController.Search.route) },
                label = { Text(text = "Search") }
            )
            BottomNavigationItem(
                selected = currentRoute == ScreenController.Add.route,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(ScreenController.Add.route)
                },
                icon = { Icon(Icons.Default.AddCircle, contentDescription = ScreenController.Add.route) },
                label = { Text(text = "Add") }
            )
            BottomNavigationItem(
                selected = currentRoute == ScreenController.BottomBarProfile.route,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(ScreenController.BottomBarProfile.route)
                },
                icon = { Icon(Icons.Default.AccountCircle, contentDescription = ScreenController.BottomBarProfile.route) },
                label = { Text(text = "Profile") }
            )
            BottomNavigationItem(
                selected = currentRoute == ScreenController.Cart.route,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(ScreenController.Cart.route)
                },
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
