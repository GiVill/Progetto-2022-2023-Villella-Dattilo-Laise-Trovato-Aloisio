package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.AuthApi
import com.example.vintedandroid.swagger.client.apis.OfferApi

class OfferViewModel(application: Application) : ViewModel() {
    private val application = application

    fun getOffers() {
        //val offers = OfferApi().getAll()
        Log.i("OfferViewModel::class", "getOffers: $ offers")
        //return offers
    }
}