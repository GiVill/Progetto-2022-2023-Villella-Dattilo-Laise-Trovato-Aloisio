package com.example.vintedandroid.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.R
import com.example.vintedandroid.swagger.client.models.BuyingOfferDto
import com.example.vintedandroid.view.config.PersonalizedAsyncImage
import com.example.vintedandroid.viewmodel.OfferViewModel


@Composable
fun OfferActivity(offerViewModel: OfferViewModel) {

    var offers = offerViewModel.getOffers()

    LazyColumn{
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable(onClick = { /* Open item details activity */ }),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                { Text(text = stringResource(R.string.your_offers)) }
            }
        }
        if(offers.isNotEmpty()) {
            items(offers) { item ->
                ListOffers(item, offerViewModel)
                Divider()
            }
        }else {
            item { NoOffers() }
        }
    }
}

@Composable
fun ListOffers(item : BuyingOfferDto,offerViewModel: OfferViewModel) {
    var insertion = offerViewModel.getInsertionByOffer(item.insertionId)
    if(insertion.id != null){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable(onClick = { /* Open item details activity */ }),
            //elevation = 4.dp
        ){
            Column {
                Text(insertion.title, modifier = Modifier.align(CenterHorizontally))
                Divider()
                //PersonalizedAsyncImage(imageName = insertion.imageName, subject = "Insertion Image")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically, // Allinea verticalmente gli elementi
                    horizontalArrangement = Arrangement.SpaceBetween // Spaziatura tra gli elementi orizzontali
                ) {
                    Text(stringResource(R.string.price)+ item.price)
                    Text(stringResource(R.string.status)+ item.status)
                    Button(onClick = {
                        offerViewModel.deleteOffer(item)
                    }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close Icon")
                    }
                }
            }
        }
    }
}

@Composable
fun NoOffers() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { /* Open item details activity */ }),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        { Text(text = stringResource(R.string.no_offers)) }
    }

}