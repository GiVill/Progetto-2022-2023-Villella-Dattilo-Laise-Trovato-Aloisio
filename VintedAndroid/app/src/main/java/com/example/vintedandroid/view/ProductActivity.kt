package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.vintedandroid.R
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.view.config.PersonalizedAsyncImage
import com.example.vintedandroid.view.config.createPersonalizedTextfield
import com.example.vintedandroid.viewmodel.HomeViewModel
import com.example.vintedandroid.viewmodel.ProductViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProductActivity(searchedProduct: MutableState<BasicInsertionDto>, itemsInCart: MutableList<BasicInsertionDto?>, homeViewModel: HomeViewModel, productViewModel: ProductViewModel, application: Context) {

    var showMakeOfferDialog by remember { mutableStateOf(false) }
    var showContactVendorDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //PersonalizedAsyncImage(imageName = searchedProduct.value.imageName, subject = "HomeActivity::class")

            if(!searchedProduct.value.userId.equals(LoggedUserDetails.getInstance().getCurrentUser().id)){
                PublicItemCard(
                    searchedProduct = searchedProduct,
                    homeViewModel = homeViewModel,
                    itemsInCart = itemsInCart,
                    productViewModel = productViewModel,
                    application = application
                )
            }
            else{
                PrivateItemCard(searchedProduct, productViewModel)
            }
        }
    }
}


@Composable
private fun searchedProductDisplayInfo(searchedProduct: MutableState<BasicInsertionDto>){

    Text(
        text = searchedProduct.value.title,
        modifier = Modifier.padding(16.dp), fontSize = 30.sp
    )
    searchedProduct.value.description.let {
        Text(
            text = it,
            modifier = Modifier.padding(16.dp)
        )
    }
    Text(text = "$${searchedProduct.value.price}")

}

@Composable
fun makeOfferActivity(onDismiss: () -> Unit, basicInsertionDto: BasicInsertionDto, productViewModel: ProductViewModel, application: Context) {
    val priceField = remember { mutableStateOf(TextFieldValue()) }
    val priceRegex = "^\\d+\$".toRegex()

    Dialog(onDismissRequest = { onDismiss() }) {

        Card(
            Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Column {
                createPersonalizedTextfield(
                    textField = priceField,
                    name = stringResource(R.string.price),
                    icon = Icons.Default.Money,
                    regexPattern = priceRegex
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(
                       onClick = {onDismiss()},
                    ) {
                        Text(stringResource(R.string.go_back))
                    }
                    Button(
                        onClick = {
                        basicInsertionDto.price = priceField.value.text.toFloat()
                        productViewModel.makeOffer(basicInsertionDto)
                        Toast.makeText(application.applicationContext, "Offert Sended", Toast.LENGTH_SHORT).show()
                        onDismiss()

                        }) {
                        Text(stringResource(R.string.do_offer))
                    }
                }
            }
        }
    }
}

@Composable
fun contactVendor(onDismiss: () -> Unit,searchedProduct: BasicInsertionDto, productViewModel: ProductViewModel, application: Context) {

    val messageField = remember { mutableStateOf(TextFieldValue()) }
    val messageRegex = "^\\s+\$".toRegex()

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Column {
                createPersonalizedTextfield(
                    textField = messageField,
                    name = stringResource(R.string.insert_message),
                    icon = Icons.Default.Message,
                    regexPattern = messageRegex
                )

                Button(
                    onClick = {
                        productViewModel.sendMessageToVendor(searchedProduct, messageField.value.text)
                        Toast.makeText(application.applicationContext, "Message Sended", Toast.LENGTH_SHORT).show()
                        onDismiss()

                    }) {
                    Text(stringResource(R.string.send_message))
                }
            }
        }
    }
}

@Composable
fun PublicItemCard(searchedProduct: MutableState<BasicInsertionDto>, homeViewModel: HomeViewModel, itemsInCart: MutableList<BasicInsertionDto?>, productViewModel: ProductViewModel, application: Context) {

    var showMakeOfferDialog by remember { mutableStateOf(false) }
    var showContactVendorDialog by remember { mutableStateOf(false) }


    searchedProductDisplayInfo(searchedProduct = searchedProduct)
    Button(
        onClick = {
            homeViewModel.insertBasicInsertionDtoOnCartDto(item= searchedProduct.value, itemsInCart= itemsInCart) },
        //modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Text(text = stringResource(R.string.add_cart))
    }
    Button(
        onClick = {
            showContactVendorDialog = true
        },
        //modifier = Modifier.align(Alignment.CenterHorizontally)
    ){
        Text(text = stringResource(R.string.contact_vendor)+ "(Da testare)")
    }
    Button(
        onClick = {
            showMakeOfferDialog = true
        },
        //modifier = Modifier.align(Alignment.CenterHorizontally)
    ){
        Text(text = stringResource(R.string.offer))
    }
    if (showMakeOfferDialog) {
        makeOfferActivity(onDismiss = { showMakeOfferDialog = false }, searchedProduct.value, productViewModel, application)
    }
    if(showContactVendorDialog){
        contactVendor(onDismiss = { showContactVendorDialog = false }, searchedProduct.value, productViewModel, application)
    }

}

@Composable
fun PrivateItemCard(searchedProduct: MutableState<BasicInsertionDto>, productViewModel: ProductViewModel) {

    var allOffers = searchedProduct.value.id?.let { productViewModel.getAllOffersFromInsertion(it) }


    searchedProductDisplayInfo(searchedProduct = searchedProduct)
    Text(stringResource(R.string.view_offers))
    Divider()
    Column(
        Modifier
            .fillMaxWidth()
            .padding(4.dp))
    {
        if (allOffers != null) {
            for (offer in allOffers) {
                Log.i("offer", "${offer}")

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        //verticalAlignment = Alignment.CenterVertically, // Allinea verticalmente gli elementi
                        horizontalArrangement = Arrangement.SpaceBetween // Spaziatura tra gli elementi orizzontali
                    ){
                        Column {
                            Text(text = "${offer.price}")
                            Text(text = "${offer.status}")
                        }
                        Spacer(modifier = Modifier.width(130.dp))
                        Column{

                            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {

                                Button(onClick = {
                                    productViewModel.acceptOffer(offer)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Check Icon"
                                    )
                                }
                                Button(onClick = { productViewModel.deleteOffer(offer) },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close Icon",
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun ProductScreenPreview() {
    var searchedProduct = remember {
        mutableStateOf(BasicInsertionDto(1L,"null", Float.MIN_VALUE,null,null,null,null,null,"",BasicInsertionDto.Brand.ADIDAS,BasicInsertionDto.Category.ABBIGLIAMENTO, 2L))
    }
    ProductScreen(searchedProduct)
}
 */