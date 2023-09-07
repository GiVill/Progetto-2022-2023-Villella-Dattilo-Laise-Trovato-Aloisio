package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.CartDto
import com.example.vintedandroid.swagger.client.apis.OrderApi
import com.example.vintedandroid.swagger.client.models.OrderDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : ViewModel() {

    private val application = application

    fun getAll(): Flow<MutableList<CartDto>> {
        return AppDatabase.getInstance(context = application.applicationContext).cartDao().getAll()
    }

    fun removeItemsInCartFromDatabase(item: CartDto){
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(context = application.applicationContext).cartDao().delete(item)
            Log.i("CartViewModel::class", "Item : $item removed from the cart")
        }
    }

    fun deleteAll(){
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(context = application.applicationContext).cartDao().deleteAll()
        }
    }

    fun createOrder(newOrder : OrderDto) {
        val order =OrderApi().userAddOrder(newOrder)
        Log.i("orderRetun",order.toString())
        if(order.id != 0L){
            deleteAll()
            Toast.makeText(application.applicationContext, "Order Created", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(application.applicationContext, "ERROR!", Toast.LENGTH_SHORT).show()
        }
    }

}