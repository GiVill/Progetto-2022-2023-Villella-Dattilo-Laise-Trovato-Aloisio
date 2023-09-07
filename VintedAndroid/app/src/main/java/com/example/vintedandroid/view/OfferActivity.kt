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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.R
import com.example.vintedandroid.swagger.client.models.BuyingOfferDto
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
                { Text(text = stringResource(R.string.my_offers)) }
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
fun ListOffers(offer : BuyingOfferDto,offerViewModel: OfferViewModel) {
    var insertion = offerViewModel.getInsertionByOffer(offer.insertionId)
    val statusColor = when (offer.status) {
        BuyingOfferDto.Status.PENDING -> Color.Magenta
        BuyingOfferDto.Status.APPROVED -> Color.Green
        BuyingOfferDto.Status.REFUSED -> Color.Red
        else -> Color.Black // Colore predefinito per gli altri stati
    }
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
                    Text(stringResource(R.string.price)+" "+offer.price+"$")
                    val statusText = buildAnnotatedString {
                        append(stringResource(id = R.string.status))
                        append(" ")
                        withStyle(style = SpanStyle(color = statusColor)) {
                            append(offer.status.toString())
                        }

                    }

                    Text(
                        text = statusText
                    )
                    if(offer.status == BuyingOfferDto.Status.PENDING) {
                        Button(onClick = {
                            Log.i("OFFER",offer.toString())
                            offerViewModel.deleteOffer(offer)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon"
                            )
                        }
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