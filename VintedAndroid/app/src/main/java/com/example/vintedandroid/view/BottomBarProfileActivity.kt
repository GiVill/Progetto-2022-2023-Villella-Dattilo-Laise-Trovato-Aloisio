package com.example.vintedandroid.view


import android.annotation.SuppressLint

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
import androidx.compose.material.icons.filled.EuroSymbol
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vintedandroid.R
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.theme.Typography
import com.example.vintedandroid.viewmodel.CartViewModel
import com.example.vintedandroid.viewmodel.UserViewModel


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBarProfileActivity(navController: NavController, userViewModel: UserViewModel, cartViewModel: CartViewModel) {

    val userFromDB1: State<UserDatabaseDto?> = userViewModel.getAllUserFromRoomDatabase().collectAsState(initial = null)

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.account_settings),
            modifier = Modifier.padding(10.dp),
            style = TextStyle(fontSize = Typography.titleLarge.fontSize)
        )
        Column {
            if(userFromDB1.value != null) {
                Card(onClick = {
                    navController.navigate("profile")
                }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(R.string.default_account),
                            modifier = Modifier
                                .padding(10.dp)
                                .size(size = 48.dp)
                        )
                        Column(modifier = Modifier.weight(1f)) {

                            if (userFromDB1.value != null) {
                                Text(text = userFromDB1.value!!.nickName)
                            }
                            Text(text = stringResource(R.string.see_your_profile))
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = stringResource(R.string.default_account)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))

                SimilarButton(navController = navController, text = stringResource(R.string.My_Insertions), navigateTo = ScreenController.MyInsertion.route, icon = Icons.Filled.MyLocation)
                SimilarButton(navController = navController, text = stringResource(R.string.your_orders), navigateTo = ScreenController.Order.route, icon = Icons.Filled.EuroSymbol)
                SimilarButton(navController = navController, text = stringResource(R.string.your_offers), navigateTo = ScreenController.Offer.route, icon = Icons.Filled.LocalOffer)
                SimilarButton(navController = navController, text = stringResource(R.string.send_feedback), navigateTo = ScreenController.Feedback.route, icon = Icons.Filled.Info)
                SimilarButton(navController = navController, text = stringResource(R.string.reset_password), navigateTo = ScreenController.UpdatePassword.route, icon = Icons.Filled.LockReset)

                logoutButton(userFromDB = userFromDB1, navController = navController, userViewModel= userViewModel,cartViewModel = cartViewModel)

            }else{ noUserLoggedIn(navController = navController) }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SimilarButton(navController: NavController, text: String, navigateTo: String, icon :ImageVector){

    Card(onClick = {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun logoutButton(userFromDB : State<UserDatabaseDto?>, navController: NavController, userViewModel: UserViewModel, cartViewModel: CartViewModel){
    Card(onClick = {
               userFromDB.value?.let { userViewModel.deleteUser(it) }
                cartViewModel.deleteAll()
                navController.popBackStack()
                navController.navigate(ScreenController.Login.route)
    }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.ExitToApp,
                contentDescription = stringResource(R.string.default_account),
                modifier = Modifier
                    .padding(10.dp)
            )
            Text(text = stringResource(R.string.logout))
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
        Text(text = stringResource(R.string.no_user_logged))
        Button(onClick = {
            navController.navigate(ScreenController.Login.route)
        }){
            Text(text = stringResource(R.string.go_to_login_page))
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
