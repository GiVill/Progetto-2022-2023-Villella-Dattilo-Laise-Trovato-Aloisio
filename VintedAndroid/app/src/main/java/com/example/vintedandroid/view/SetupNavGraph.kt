package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vintedandroid.Item
import com.example.vintedandroid.client.apis.InsertionApi
import com.example.vintedandroid.client.models.UserDto
import com.example.vintedandroid.model.AppDatabase
import org.springframework.context.ApplicationContext
import java.util.UUID

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupNavGraph(navController: NavHostController, searchText: MutableState<String>, application: Context) {

    //var user = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll() //Get di un utente


    val itemsInCart = remember {
        mutableStateListOf<Item?>()
    }

    val searchString = remember {
        mutableStateListOf<String>()
    }

    //TODO user e user1 vanno cambiati!
    val user = UserDto(UUID.randomUUID().toString(),"ciao","Boh","ciaoBoh","ciao@yahoo.it",
        "10-05-2001", UserDto.Gender.MALE,"via napoli",8,"Lamezia",21312,
        "Italy","asdojad")

    val user1 = UserDto(UUID.randomUUID().toString(),"ciao","Boh","ciaoBoh")


    NavHost(navController = navController, startDestination = "home") {
    //NavHost(navController = navController, startDestination = ScreenController.Home.route ){

        composable(route = ScreenController.Home.route){
            HomeScreen(itemsInCart)
        }
        composable("search") {
            SearchScreen(searchText)
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
        composable(route = ScreenController.Login.route){
            LoginScreen(navController)
        }
        composable(route = ScreenController.Register.route){
            RegistrationScreen(navController)
        }
    }

}