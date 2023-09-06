package com.example.vintedandroid.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.vintedandroid.R
import com.example.vintedandroid.swagger.client.models.OrderDto
import com.example.vintedandroid.swagger.client.models.PageOrderDto
import com.example.vintedandroid.viewmodel.OrderViewModel


@Composable
fun OrderActivity(orderViewModel: OrderViewModel) {
    var pageOrder = 0

    val orderHistory = remember { mutableListOf<PageOrderDto>() }

    var order by remember { mutableStateOf(orderViewModel.getOrders(pageOrder)) }

    Log.i("PAGES", order.toString())


    LazyColumn(modifier = Modifier.fillMaxSize()){
        while(order.empty != true){
            orderHistory.add(order)
            order = orderViewModel.getOrders(pageOrder)
            pageOrder += 1
        }
        if(orderHistory.isNotEmpty()) {
            items(orderHistory) { item ->
                Log.i("ITEM", item.toString())
                for(order in item.results){
                    ListOrder(item = order, orderViewModel)
                }
            }
        }
        else{
            item {
                NoOrders()
            }
        }
    }
}

/*
 CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
        )
 */

@Composable
fun ListOrder(item: OrderDto, orderViewModel: OrderViewModel) {

    var showPopupDialogOrder by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable(onClick = {
                showPopupDialogOrder = true
            })
    ){
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically, // Allinea verticalmente gli elementi
                horizontalArrangement = Arrangement.SpaceBetween // Spaziatura tra gli elementi orizzontali
            ) {
                Text(item.id.toString())
                Text(item.paymentMethod.toString())
                Text(item.total.toString())
                Text(item.date.toString())
            }
        }
        if (showPopupDialogOrder) {
            PopupDialogOrder(onDismiss = { showPopupDialogOrder = false }, orderViewModel = orderViewModel, item.insertionIdList)
        }
    }
}

@Composable
fun NoOrders() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        { Text(text = stringResource(R.string.no_orders)) }
    }

}


@Composable
fun PopupDialogOrder(onDismiss: () -> Unit, orderViewModel: OrderViewModel, insertionIdList: Array<Long>?) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            if (insertionIdList != null) {
                Column {
                    Text(text = stringResource(R.string.order_details), Modifier.align(CenterHorizontally))
                    Divider()
                    for (insertionId in insertionIdList) {

                        //TODO Andrebbero presi solo gli ordini pubblici
                        var insertion = orderViewModel.getInsertionById(insertionId)
                        if(!insertion.title.equals("")) {
                            Row {
                                Text("${insertion.title}", Modifier.padding(5.dp))
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
fun OrderScreenPreview() {
    OrderActivity()
}

 */