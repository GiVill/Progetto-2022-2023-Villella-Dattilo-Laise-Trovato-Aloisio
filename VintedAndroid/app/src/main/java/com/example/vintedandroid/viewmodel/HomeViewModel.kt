package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.PageBasicInsertionDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.CartDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class HomeViewModel(application: Application) : ViewModel() {

    private val application = application

    fun getAllInsertion(): PageBasicInsertionDto {
        var insertion = InsertionApi().userGetAll(0)
        Log.i("HomeViewModel::class", "getAllInsertion -> ${insertion.results}")
        return insertion
    }

    fun getAllInsertionByCategory(category: String): PageBasicInsertionDto {
        var insertion = InsertionApi().getByCategory(category, 0)
        Log.i("HomeViewModel::class", "getAllInsertionByCategory -> $insertion")
        return insertion
    }

    fun insertBasicInsertionDtoOnCartDto(item: BasicInsertionDto, itemsInCart: MutableList<BasicInsertionDto?>) {
        if (!itemsInCart.contains(item)) {
            itemsInCart.add(item)
            CoroutineScope(Dispatchers.IO).launch {
                convertBasicInsertionDtoToCartDto(item).let {
                    AppDatabase.getInstance(context = application.applicationContext).cartDao().insert(it)
                }
                Log.i("HomeViewModel", "Item added on DB!")
            }
        } else {
            Log.i("HomeViewModel", "Cannot add the item because is already in the cart!")
        }
    }

    private fun convertBasicInsertionDtoToCartDto(item: BasicInsertionDto): CartDto {

        return CartDto(
            title = item.title,
            price = item.price,
            description = item.description,
            //condition = item.condition,
            //creationDate = null,
            //creationDate = item.creationDate as LocalDate?,
            isPrivate = item.isPrivate,
            imageName = item.imageName,
            //brand = item.brand,
            //category = item.category,
            userId = item.userId
        )

    }

}