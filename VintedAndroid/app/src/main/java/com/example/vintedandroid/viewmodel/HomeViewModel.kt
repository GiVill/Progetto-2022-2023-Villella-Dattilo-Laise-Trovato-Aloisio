package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.client.apis.InsertionApi
import com.example.vintedandroid.client.models.BasicInsertionDto
import com.example.vintedandroid.client.models.PageBasicInsertionDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.CartDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class HomeViewModel(application: Application) : ViewModel() {

    private val application = application

    fun getAllInsertion(): PageBasicInsertionDto {
        var insertion = InsertionApi().all4(0)
        Log.i("HomeViewModel::class", "getAllInsertion -> $insertion")
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
                convertBasicInsertionDtoToCartDto(item)?.let {
                    AppDatabase.getInstance(context = application.applicationContext).cartDao()
                        .insert(it)
                }
                Log.i("HomeViewModel", "Item added on DB!")
            }
        } else {
            Log.i("HomeViewModel", "Cannot add the item because is already in the cart!")
        }
    }


    /*
    if (!itemsInCart.contains(item)) {
                    itemsInCart.add(item)
                    CoroutineScope(Dispatchers.IO).launch {
                        convertBasicInsertionDtoToCartDto(item)?.let {
                            AppDatabase.getInstance(context = application.applicationContext).cartDao().insert(
                                it
                            )
                        }
                    }
                    Log.i("cart", "Item added!")
                    itemsInCart.forEachIndexed { index, item ->
                        Log.i("cart", "Item $index: $item") //stampa tutto il carrello
                    }
                } else {
                    Log.i("cart", "Cannot add the item because is already in the cart!")
                    itemsInCart.forEachIndexed { index, item ->
                        Log.i("cart", "Item $index: $item") //stampa tutto il carrello
                    }
                }
     */


    /*
     fun addContact(firstName: String, lastName: String, phoneNumber: String, image: Bitmap?): Boolean { // Add contact
        return try {
            val contact = Contact(firstName = firstName, lastName = lastName, phoneNumber = phoneNumber, image = image)
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance(context = _application.applicationContext).contactDao().insert(contact = contact)
            }
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getContact(id: String) : Flow<Contact?> {
        return AppDatabase.getInstance(context = _application.applicationContext).contactDao().getContactById(id)
    }

    fun removePreferred(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            contact.preferred = false
            AppDatabase.getInstance(context = _application.applicationContext).contactDao().update(contact = contact)
        }
    }

    fun setPreferred(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            contact.preferred = true
            AppDatabase.getInstance(context = _application.applicationContext).contactDao().update(contact = contact)
        }
    }
     */

    private fun convertBasicInsertionDtoToCartDto(item: BasicInsertionDto): CartDto? {

        return item.userId?.let {
            CartDto(
                title = item.title,
                price = item.price,
                description = item.description,
                condition = item.condition,
                //creationDate = null,
                //creationDate = item.creationDate as LocalDate?,
                isPrivate = item.isPrivate,
                //endDate = null,
                //endDate = item.endDate as LocalDate?,
                imageName = item.imageName,
                brand = item.brand,
                category = item.category,
                userId = it
            )
        }

    }

}