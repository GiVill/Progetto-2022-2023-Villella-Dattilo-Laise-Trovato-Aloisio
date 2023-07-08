package com.example.vintedandroid.view

sealed class ScreenController(val route: String){
    object Home: ScreenController(route = "home")
    object Search: ScreenController(route = "search")
    object Add: ScreenController(route = "add")
    object Cart: ScreenController(route = "cart")
    object Profile: ScreenController(route = "profile")
    object BottomBarProfile: ScreenController(route = "bottom-bar-profile")
    object Favorite: ScreenController(route = "favorite")
    object Balance: ScreenController(route = "balance")
    object Setting: ScreenController(route = "setting")
    object Feedback: ScreenController(route = "feedback")
    object Login:ScreenController(route = "login")
    object Register:ScreenController(route = "register")


}
