package com.example.vintedandroid.controller

import retrofit2.http.GET

interface UserController {
    @GET("")
    fun getAllUser()
}