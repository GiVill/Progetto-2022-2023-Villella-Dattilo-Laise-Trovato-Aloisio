package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vintedandroid.client.apis.InsertionApi
import com.example.vintedandroid.client.models.BasicInsertionDto
import com.example.vintedandroid.client.models.UserDto
import com.example.vintedandroid.model.AppDatabase
import org.springframework.context.ApplicationContext
import java.util.UUID

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState",
    "RememberReturnType"
)
@Composable
fun SetupNavGraph(navController: NavHostController, searchText: MutableState<String>, application: Context) {

    //var user = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll() //Get di un utente

    val insertionApi = InsertionApi()

    val itemsInCart = remember {
        mutableStateListOf<BasicInsertionDto?>()
    }

    var searchedProduct = remember {
        mutableStateOf(BasicInsertionDto(1L,"null", Float.MIN_VALUE,null,null,null,null,null,"",BasicInsertionDto.Brand.ADIDAS,BasicInsertionDto.Category.ABBIGLIAMENTO, 2L))
    }

    /*
    //TODO user e user1 vanno cambiati!
    val user = UserDto(UUID.randomUUID().toString(),"ciao","Boh","ciaoBoh","","ciao@yahoo.it",
        "10-05-2001", UserDto.Gender.MALE,"via napoli",8,"Lamezia",21312,
        "Italy","asdojad")

    val user1 = UserDto(UUID.randomUUID().toString(),"ciao","Boh","ciaoBoh")
*/

    NavHost(navController = navController, startDestination = ScreenController.Login.route) {

        composable(route = ScreenController.Home.route){
            HomeScreen(itemsInCart, navController, searchedProduct, application)
        }
        composable(route = ScreenController.Search.route) {
            SearchScreen(searchText, insertionApi, navController, searchedProduct)
        }
        composable(route = ScreenController.Add.route){
            AddScreen(application)
        }
        composable(route = ScreenController.Cart.route){
            CartScreen(application)
        }
        composable(route = ScreenController.Profile.route){
            ProfileScreen(application)
        }
        composable(route = ScreenController.BottomBarProfile.route){
            BottomBarProfile(navController, application)
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
            LoginScreen(navController, application)
        }
        composable(route = ScreenController.Register.route){
            RegistrationScreen(navController, application)
        }
        composable(route = ScreenController.Product.route){
            ProductScreen(searchedProduct,application,itemsInCart)
        }

    }

}