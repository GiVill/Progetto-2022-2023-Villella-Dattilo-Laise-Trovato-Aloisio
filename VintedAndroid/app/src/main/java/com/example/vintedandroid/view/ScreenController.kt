package com.example.vintedandroid.view

sealed class ScreenController(val route: String){
    object Home: ScreenController(route = "home")
    object Search: ScreenController(route = "search")
    object Add: ScreenController(route = "add")
    object Cart: ScreenController(route = "cart")
    object Profile: ScreenController(route = "profile")
    object BottomBarProfile: ScreenController(route = "bottom-bar-profile")
    object Order: ScreenController(route = "order")
    object Offer: ScreenController(route = "offer")
    object Feedback: ScreenController(route = "feedback")
    object Login:ScreenController(route = "login")
    object Register:ScreenController(route = "register")
    object Product:ScreenController(route = "product")
    object UpdatePassword:ScreenController(route = "update_password")
    object Chat:ScreenController(route = "chat")
    object ChatMessage:ScreenController(route = "chatMessage")

}
