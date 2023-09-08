package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(application: Application) : ViewModel() {

    private val application = application
    private val insertions : SnapshotStateList<BasicInsertionDto> = mutableStateListOf()


    fun getAllInsertion(page: Int): SnapshotStateList<BasicInsertionDto> {
        val pageInsertion = InsertionApi().userGetAll(page)
        insertions.addAll(pageInsertion.results)
        Log.i("HomeViewModel::class", "getAllInsertion -> ${insertions}")
        return insertions
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
                Log.i("HomeViewModel::class", "Item added on DB!")
            }
            Toast.makeText(application.applicationContext, "Item Added on Cart", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(application.applicationContext, "You already have the item in the cart", Toast.LENGTH_SHORT).show()
            Log.i("HomeViewModel::class", "Cannot add the item because is already in the cart!")
        }
    }

    private fun convertBasicInsertionDtoToCartDto(item: BasicInsertionDto): CartDto {

        return CartDto(
            id = item.id,
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