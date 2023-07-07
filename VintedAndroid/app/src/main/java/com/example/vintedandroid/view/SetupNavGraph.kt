package com.example.vintedandroid.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vintedandroid.Item
import com.example.vintedandroid.client.models.UserDto

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
        composable(route = ScreenController.Search.route){
            SearchScreen()
        }
        composable(route = ScreenController.Add.route){
            AddScreen()
        }
        composable(route = ScreenController.Cart.route){
            CartScreen(itemsInCart)
        }
        composable(route = ScreenController.Profile.route){
            ProfileScreen(user) //TODO cambiare user
        }
        composable(route = ScreenController.BottomBarProfile.route){
            BottomBarProfile(navController, user1) //TODO cambiare user1
        }
        composable(route = ScreenController.Favorite.route){
            FavoriteScreen()
        }
        composable(route = ScreenController.Balance.route){
            BalanceScreen()
        }
        composable(route = ScreenController.Setting.route){
            SettingScreen()
        }
        composable(route = ScreenController.Feedback.route){
            FeedbackScreen()
        }
    }

}