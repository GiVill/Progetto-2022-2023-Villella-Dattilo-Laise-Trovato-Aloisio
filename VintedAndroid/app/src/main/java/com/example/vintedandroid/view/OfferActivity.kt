package com.example.vintedandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vintedandroid.swagger.client.models.BuyingOfferDto
import com.example.vintedandroid.viewmodel.OfferViewModel


@Composable
fun OfferActivity(offerViewModel: OfferViewModel) {

    var offers = offerViewModel.getOffers()

    LazyColumn{
        items(offers){item ->
            ListOffers(item)
        }
    }
}

@Composable
fun ListOffers(item : BuyingOfferDto) {
    Card(modifier = Modifier.fillMaxSize()){
        Text("Price "+ item.price)
        Text("Insertion "+ item.insertionId)
        Text("Status "+ item.status)
    }
}

@Composable
fun NoOffers() {
    Card(modifier = Modifier.fillMaxSize()){
        Text("You haven't done any offer")
    }
}