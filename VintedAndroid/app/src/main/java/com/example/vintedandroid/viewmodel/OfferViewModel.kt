package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.AuthApi
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.apis.OfferApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.BuyingOfferDto

class OfferViewModel() : ViewModel() {

    private val insertionApi = InsertionApi()

    fun getOffers(): Array<BuyingOfferDto>{
        val offers = OfferApi().getAllByUser()
        Log.i("OfferViewModel::class", "getOffers: $offers")
        return offers
    }

    fun getInsertionByOffer(insertionId : Long ): BasicInsertionDto{
        return insertionApi.getInsertionById(insertionId)
    }

    fun deleteOffer(buyingOfferDto: BuyingOfferDto){

        buyingOfferDto.status = BuyingOfferDto.Status.PENDING
        Log.i("OfferViewModel", "deleteOffer: $buyingOfferDto")
        OfferApi().userDeleteOffer(buyingOfferDto)
    }
}