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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState", "RememberReturnType")
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    searchText: MutableState<String>,
    application: Context,
    homeViewModel: HomeViewModel,
    userViewModel: UserViewModel,
    cartViewModel: CartViewModel,
    loginRegistrationViewModel: LoginRegistrationViewModel,
    updatePasswordViewModel: UpdatePasswordViewModel,
    offerViewModel: OfferViewModel,
    productViewModel: ProductViewModel,
    chatViewModel: ChatViewModel,
    orderViewModel: OrderViewModel,
    addViewModel: AddViewModel,
    myInsertionViewModel: MyInsertionViewModel,
    chatMessageViewModel: ChatMessageViewModel, ) {

    val insertionApi = InsertionApi()

    val itemsInCart = remember { mutableStateListOf<BasicInsertionDto?>() }


    var searchedProduct = remember {
        mutableStateOf(BasicInsertionDto(null, "", 1F, "", null, null, null, null, null, null,1L))
    }

    NavHost(navController = navController, startDestination = ScreenController.Login.route) {

        composable(route = ScreenController.Home.route){
            HomeActivity(itemsInCart, navController, searchedProduct, application, homeViewModel)
        }
        composable(route = ScreenController.Search.route) {
            SearchActivity(searchText, insertionApi, navController, searchedProduct)
        }
        composable(route = ScreenController.Add.route){
            AddActivity(addViewModel)
        }
        composable(route = ScreenController.Cart.route){
            CartActivity(cartViewModel)
        }
        composable(route = ScreenController.Profile.route){
            ProfileActivity(userViewModel, navController)
        }
        composable(route = ScreenController.BottomBarProfile.route){
            BottomBarProfileActivity(navController, userViewModel, cartViewModel)
        }
        composable(route = ScreenController.Order.route){
            OrderActivity(orderViewModel)
        }
        composable(route = ScreenController.Offer.route){
            OfferActivity(offerViewModel)
        }
        composable(route = ScreenController.Feedback.route){
            FeedbackActivity(application)
        }
        composable(route = ScreenController.Login.route){
            LoginActivity(navController, loginRegistrationViewModel, application)
        }
        composable(route = ScreenController.Register.route){
            RegistrationActivity(navController, loginRegistrationViewModel)
        }
        composable(route = ScreenController.Product.route){
            ProductActivity(searchedProduct,itemsInCart, homeViewModel, productViewModel, application, userViewModel, chatViewModel)
        }
        composable(route = ScreenController.UpdatePassword.route){
            UpdatePasswordActivity(updatePasswordViewModel, application)
        }
        composable(route = ScreenController.Chat.route){
            ChatActivity(chatViewModel, navController)
        }
        composable(route = ScreenController.MyInsertion.route){
            MyInsertionActivity(application,searchedProduct, myInsertionViewModel, navController)
        }
        composable(route = ScreenController.ChatMessage.route + "/{chatId}") { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId")
            ChatMessageActivity(chatId, chatMessageViewModel)
        }


    }

}