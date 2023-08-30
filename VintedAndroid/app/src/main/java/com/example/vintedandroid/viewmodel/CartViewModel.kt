package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.CartDto
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
            Log.i("CartScreen::class", "Item : $item removed from the cart")

        }
        /*
        CoroutineScope(Dispatchers.IO).launch {
                    itemsFromDB.remove(item)
                    AppDatabase.getInstance(context = application.applicationContext).cartDao().delete(item)
                }
                Log.i("CartScreen::class", "Item : $item removed from the cart")

         */
    }

}