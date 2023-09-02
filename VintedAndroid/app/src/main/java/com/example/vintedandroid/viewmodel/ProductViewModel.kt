package com.example.vintedandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.swagger.client.apis.ChatMessageApi
import com.example.vintedandroid.swagger.client.apis.OfferApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.BuyingOfferDto
import com.example.vintedandroid.swagger.client.models.NewMessageDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : ViewModel() {
    private val application = application

    fun makeOffer(buyingOfferDto: BuyingOfferDto){
        OfferApi().userAddBuyingOffer(buyingOfferDto)
    }

    fun sendMessageToVendor(searchedProduct: BasicInsertionDto, message: String){
        var loggedUser: UserDatabaseDto? = null
        CoroutineScope(Dispatchers.IO).launch {
            loggedUser = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getSingleUser()
        }
        val newMessageDto = convertStringToNewMessageDto(loggedUser, searchedProduct, message)
        ChatMessageApi(application).insertMessage(newMessageDto)
    }

    fun convertBasicInsertionDtoToBuyingOfferDto(basicInsertionDto : BasicInsertionDto): BuyingOfferDto?{

        return basicInsertionDto.id?.let {
            BuyingOfferDto(
                id = null,
                price = basicInsertionDto.price,
                status = null,
                insertionId = it,
                userId = basicInsertionDto.userId
            )
        }
    }

    fun convertStringToNewMessageDto(loggedUser: UserDatabaseDto?, searchedProduct: BasicInsertionDto, message: String): NewMessageDto{

        if (loggedUser != null) {
            return NewMessageDto(
                sender = loggedUser.id,
                reciver = searchedProduct.userId,
                message = message,
                chatId = null
            )
        }
        return NewMessageDto(null, null, null, null)
    }


}