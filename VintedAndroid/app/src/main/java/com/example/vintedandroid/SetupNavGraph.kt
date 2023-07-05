package com.example.vintedandroid

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vintedandroid.client.models.UserDto
import com.example.vintedandroid.theme.ui.AccountActivity
import com.example.vintedandroid.theme.ui.PersonalInfo

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupNavGraph(navController: NavHostController) {

    val itemsInCart = remember {
        mutableStateListOf<Item?>()
    }

    //TODO user e user1 vanno cambiati!
    val user = UserDto(1L,"ciao","Boh","ciaoBoh","ciao@yahoo.it",
        "10-05-2001", UserDto.Gender.MALE,"via napoli",8,"Lamezia",21312,
        "Italy","asdojad")

    val user1 = UserDto(1L,"ciao","Boh","ciaoBoh")



    NavHost(navController = navController, startDestination = ScreenController.Home.route ){

        composable(route = ScreenController.Home.route){
            HomeScreen(itemsInCart)
        }
        composable(route = ScreenController.Cart.route){
            CartScreen(itemsInCart)
        }
        composable(route = ScreenController.Profile.route){
            PersonalInfo(user) //TODO cambiare user
        }
        composable(route = ScreenController.Account.route){
            AccountActivity(navController, user1) //TODO cambiare user1
        }
    }

}