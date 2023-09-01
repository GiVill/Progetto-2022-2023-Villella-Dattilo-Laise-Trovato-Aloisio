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
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.viewmodel.CartViewModel
import com.example.vintedandroid.viewmodel.HomeViewModel
import com.example.vintedandroid.viewmodel.LoginRegistrationViewModel
import com.example.vintedandroid.viewmodel.OfferViewModel
import com.example.vintedandroid.viewmodel.UpdatePasswordViewModel
import com.example.vintedandroid.viewmodel.UserViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState", "RememberReturnType")
@Composable
fun SetupNavGraph(navController: NavHostController, searchText: MutableState<String>, application: Context, homeViewModel: HomeViewModel, userViewModel: UserViewModel, cartViewModel: CartViewModel, loginRegistrationViewModel: LoginRegistrationViewModel, updatePasswordViewModel: UpdatePasswordViewModel, offerViewModel: OfferViewModel) {

    val insertionApi = InsertionApi()

    val itemsInCart = remember { mutableStateListOf<BasicInsertionDto?>() }


    var searchedProduct = remember {
        mutableStateOf(BasicInsertionDto(null, "", 1F, "", null, null, null, null, null, null,1L))
    }

    NavHost(navController = navController, startDestination = ScreenController.Login.route) {

        composable(route = ScreenController.Home.route){
            HomeScreen(itemsInCart, navController, searchedProduct, application, homeViewModel)
        }
        composable(route = ScreenController.Search.route) {
            SearchScreen(searchText, insertionApi, navController, searchedProduct)
        }
        composable(route = ScreenController.Add.route){
            AddScreen(application)
        }
        composable(route = ScreenController.Cart.route){
            CartScreen(cartViewModel)
        }
        composable(route = ScreenController.Profile.route){
            ProfileScreen(userViewModel)
        }
        composable(route = ScreenController.BottomBarProfile.route){
            BottomBarProfile(navController, userViewModel, cartViewModel)
        }
        composable(route = ScreenController.Order.route){
            OrderScreen()
        }
        composable(route = ScreenController.Offer.route){
            OfferActivity(offerViewModel = offerViewModel)
        }
        composable(route = ScreenController.Feedback.route){
            FeedbackScreen()
        }
        composable(route = ScreenController.Login.route){
            LoginScreen(navController, userViewModel, loginRegistrationViewModel)
        }
        composable(route = ScreenController.Register.route){
            RegistrationScreen(navController, application, loginRegistrationViewModel)
        }
        composable(route = ScreenController.Product.route){
            ProductScreen(searchedProduct,itemsInCart, homeViewModel)
        }
        composable(route = ScreenController.UpdatePassword.route){
            UpdatePasswordScreen(updatePasswordViewModel)
        }

    }

}