package com.example.vintedandroid.view


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.EuroSymbol
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Html
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoveToInbox
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ResetTv
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vintedandroid.R
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.theme.Typography
import com.example.vintedandroid.viewmodel.CartViewModel
import com.example.vintedandroid.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//TODO: CAMBIARE USER CON INSERZIONE
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarProfile(navController: NavController, application: Context, userViewModel: UserViewModel, cartViewModel: CartViewModel) {

//  var userFromDB = remember { mutableStateListOf<UserDatabaseDto>() }
    val userFromDB1 = userViewModel.getAllUserFromRoomDatabase()
/*
    LaunchedEffect(Unit) {
        if (userFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()
            }
            userFromDB.clear()
            if(databaseItems.isNotEmpty()) {
                userFromDB.addAll(databaseItems)
            }
        }
    }

 */

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.account_settings),
            modifier = Modifier.padding(10.dp),
            style = TextStyle(fontSize = Typography.titleLarge.fontSize)
        )
        Column {
            //if(userFromDB1 != null) {
                Card(onClick = { //navController.popBackStack();
                    navController.navigate("profile") }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(R.string.default_account),
                            modifier = Modifier
                                .padding(10.dp)
                                .size(size = 48.dp)
                        )
                        Column(modifier = Modifier.weight(1f)) {
                           // Text(text = userFromDB1[0].nickName)
                            if (userFromDB1 != null) {
                                Text(text = userFromDB1.nickName)
                            }
                            Text(text = "See your profile")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = stringResource(R.string.default_account)
                        )
                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                SimilarButton(navController = navController, text = "Your Orders", navigateTo = ScreenController.Order.route, icon = Icons.Filled.EuroSymbol)
                SimilarButton(navController = navController, text = "Your Offer", navigateTo = ScreenController.Offer.route, icon = Icons.Filled.LocalOffer)
                SimilarButton(navController = navController, text = "Send Feedback (For the MEME)", navigateTo = ScreenController.Feedback.route, icon = Icons.Filled.Info)
                SimilarButton(navController = navController, text = "Reset Password", navigateTo = ScreenController.UpdatePassword.route, icon = Icons.Filled.LockReset)

                logoutButton(userFromDB = userFromDB1, application = application, navController = navController, userViewModel= userViewModel,cartViewModel = cartViewModel)

            //}else{ noUserLoggedIn(navController = navController) }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SimilarButton(navController: NavController, text: String, navigateTo: String, icon :ImageVector){

    Card(onClick = { //navController.popBackStack();
        navController.navigate(navigateTo) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(R.string.default_account),
                modifier = Modifier
                    .padding(10.dp)
            )
            Text(text = text)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.default_account)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun logoutButton(userFromDB : UserDatabaseDto?, application: Context, navController: NavController, userViewModel: UserViewModel,cartViewModel: CartViewModel){
    Card(onClick = {
        //CoroutineScope(Dispatchers.Main).launch {

           if (userFromDB != null) {
                userViewModel.deleteUser(userFromDB)
                cartViewModel.deleteAll()
                //userFromDB.remove(userFromDB[0])
                //AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().delete(userFromDB)
                //AppDatabase.getInstance(context = application.applicationContext).cartDao().deleteAll()
         //   }
         //   withContext(Dispatchers.Main) {
                //navController.popBackStack();
                navController.navigate("login")
         //   }
        }

    }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.ExitToApp,
                contentDescription = stringResource(R.string.default_account),
                modifier = Modifier
                    .padding(10.dp)
            )
            Text(text = "Logout")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.default_account)
            )
        }
    }
}

@Composable
private fun noUserLoggedIn(navController: NavController){
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(text = "NO USER LOGGED")
        Button(onClick = { //navController.popBackStack();
            navController.navigate(ScreenController.Login.route) }){
            Text(text = "Go to Login page")
        }
    }
}

@Composable
fun resetPassword(navController: NavHostController) {

    androidx.compose.material.Text(
        text = "Reset Password",
        modifier = Modifier.clickable {
            // Handle the click action here, like navigating to the reset password screen
            navController.navigate(ScreenController.Home.route)
        })
}

/*
@Preview(showBackground = true)
@Composable
fun BottomBarProfilePreview() {

    val navController = rememberNavController()

    val user = UserDto(UUID.randomUUID().toString(),"ciao","Boh","ciaoBoh")
    //BottomBarProfile(navController, user)
}
 */
