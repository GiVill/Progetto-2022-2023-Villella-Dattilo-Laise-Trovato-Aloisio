package com.example.vintedandroid.view


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

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
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vintedandroid.R
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.theme.Typography
import com.example.vintedandroid.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//TODO: CAMBIARE USER CON INSERZIONE
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarProfile(navController: NavController, application: Context, userViewModel: UserViewModel) {

//    var userFromDB = remember { mutableStateListOf<UserDatabaseDto>() }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

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
            //if(userFromDB1.isNotEmpty()) {
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

                SimilarButton(navController = navController, text = "Favorite", navigateTo = ScreenController.Favorite.route, Icons.Filled.Favorite)
                SimilarButton(navController = navController, text = "Balance", navigateTo = ScreenController.Balance.route, icon = Icons.Filled.PlayArrow)
                SimilarButton(navController = navController, text = "Settings", navigateTo = ScreenController.Setting.route, icon = Icons.Filled.Settings)
                SimilarButton(navController = navController, text = "INVIA FEEDBACK (PER IL MEME)", navigateTo = ScreenController.Feedback.route, icon = Icons.Filled.Info)

                //logoutButton(userFromDB = userFromDB1, application = application, navController = navController)

            //}else{ noUserLoggedIn(navController = navController, currentBackStackEntry) }

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
private fun logoutButton(userFromDB : SnapshotStateList<UserDatabaseDto>, application: Context, navController: NavController){
    Card(onClick = {
        CoroutineScope(Dispatchers.Main).launch {

            if (userFromDB.isNotEmpty()) {
                //userFromDB.remove(userFromDB[0])
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().delete(userFromDB[0])
                AppDatabase.getInstance(context = application.applicationContext).cartDao().deleteAll()
            }
            withContext(Dispatchers.Main) {
                //navController.popBackStack();
                navController.navigate("login")
            }
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
private fun noUserLoggedIn(navController: NavController, currentBackStackEntry: NavBackStackEntry?){
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(text = "NO USER LOGGED")
        Button(onClick = { //navController.popBackStack();
            Log.i("BottomBarProfile::class", "currentBackStackEntry: $currentBackStackEntry")
            Log.i("BottomBarProfile::class", "navController.currentBackStackEntry?.destination?.route: ${navController.currentBackStackEntry?.destination?.route}")
            Log.i("BottomBarProfile::class", "navController.currentBackStackEntry?.destination?.route: ${navController.currentBackStackEntry?.arguments}")

            navController.navigate(ScreenController.Login.route) }){
            Text(text = "Go to Login page")
        }
    }
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
