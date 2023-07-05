package com.example.vintedandroid

sealed class ScreenController(val route: String){
    object Home:ScreenController(route = "home")
    object Cart:ScreenController(route = "cart")
    object Profile:ScreenController(route = "profile")
    object Account:ScreenController(route = "account")
}
