package com.example.vintedandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vintedandroid.viewmodel.OfferViewModel


@Composable
fun OfferActivity(offerViewModel: OfferViewModel) {

    var offers = null//offerViewModel.getOffer()

    Box(modifier = Modifier.fillMaxSize()){

        if(offers != null){
            ListOffers()
        }else{
            NoOffers()
        }

    }
}

@Composable
fun ListOffers() {
    Card(modifier = Modifier.fillMaxSize()){
        Text("Informazioni delle offerte")
    }
}

@Composable
fun NoOffers() {
    Card(modifier = Modifier.fillMaxSize()){
        Text("You haven't done any offer")
    }
}