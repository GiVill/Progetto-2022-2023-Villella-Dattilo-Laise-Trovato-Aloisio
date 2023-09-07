package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.RadioButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.example.vintedandroid.R
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.model.dto.CartDto
import com.example.vintedandroid.swagger.client.models.Order
import com.example.vintedandroid.swagger.client.models.OrderDto
import com.example.vintedandroid.viewmodel.CartViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CartActivity(cartViewModel: CartViewModel) {

    val cartItemsState: MutableList<CartDto> by cartViewModel.getAll().collectAsState(initial = mutableListOf())

    ListOfItem(cartItemsState = cartItemsState, cartViewModel = cartViewModel)
}

@Composable
fun ListOfItem(cartItemsState: MutableList<CartDto>, cartViewModel: CartViewModel) {

    val radioOptions = listOf(Order.PaymentMethod.PAYPAL, Order.PaymentMethod.CARD, Order.PaymentMethod.MARK)
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    if (cartItemsState.isNotEmpty()) {
        var totalCost : Float = 0f
        var insertionIdList: MutableList<Long> = mutableListOf()
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(cartItemsState) { cartItem ->
                    Log.i("Cart", "item in cart is: $cartItem")
                totalCost += cartItem.price
                cartItem.id?.let { insertionIdList.add(it) }
                    ItemsInCart(
                        item = cartItem,
                        cartViewModel = cartViewModel
                    )
                }
            item {
                Column(Modifier.selectableGroup()) {
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text) },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = text.toString(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                            }
                        }
                }
            }
            item {
                Button(onClick = {
                    var newOrder = LoggedUserDetails.getInstance().getCurrentUser().id?.let {
                        OrderDto(0,null,selectedOption.toString(),insertionIdList.toTypedArray(),totalCost,
                            it
                        )
                    }
                    if (newOrder != null) {
                        cartViewModel.createOrder(newOrder)
                    }
                }) {
                    Text(stringResource(R.string.create_order))
                }
            }
            }
        } else {
            NoItemsInCart()
        }
}

@Composable
fun ItemsInCart(item : CartDto, cartViewModel: CartViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {  }),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = item.title)
            Spacer(modifier = Modifier.height(8.dp))
            item.description?.let { Text(text = it) }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$${item.price}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { cartViewModel.removeItemsInCartFromDatabase(item) })

            { Text(text = stringResource(R.string.discard)) }
        }
    }
}

@Composable
fun NoItemsInCart() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {  }),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        { Text(text = stringResource(R.string.no_items_in_cart)) }
    }

}