package com.example.vintedandroid.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.swagger.client.apis.ChatMessageApi
import com.example.vintedandroid.swagger.client.apis.OfferApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.BuyingOfferDto
import com.example.vintedandroid.swagger.client.models.NewMessageDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Context) : ViewModel() {
    private val application = application

    fun makeOffer(basicInsertionDto: BasicInsertionDto){
        val buyingOffer = convertBasicInsertionDtoToBuyingOfferDto(basicInsertionDto)
        Log.i("Offer:",buyingOffer.toString())
        OfferApi().userAddBuyingOffer(buyingOffer!!)
    }

    fun sendMessageToVendor(searchedProduct: BasicInsertionDto, message: String){
        var loggedUser: UserDatabaseDto? = null
        CoroutineScope(Dispatchers.IO).launch {
            loggedUser = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getSingleUser()
        }
        val newMessageDto = convertStringToNewMessageDto(loggedUser, searchedProduct, message)
        //ChatMessageApi(application).insertMessage(newMessageDto)
    }

    fun getAllOffersFromInsertion(insertionId: Long): Array<BuyingOfferDto>{
        return OfferApi().userGetAllByInsertionId(insertionId)
    }

    fun acceptOffer(buyingOfferDto: BuyingOfferDto){
        OfferApi().acceptOffers(buyingOfferDto)
    }

    fun deleteOffer(buyingOfferDto: BuyingOfferDto){
        OfferApi().userDeleteOffer(buyingOfferDto)
    }

    fun convertBasicInsertionDtoToBuyingOfferDto(basicInsertionDto : BasicInsertionDto): BuyingOfferDto?{

        return basicInsertionDto.id?.let {
            LoggedUserDetails.getInstance().getCurrentUser().id?.let { it1 ->
                BuyingOfferDto(
                    id = null,
                    price = basicInsertionDto.price,
                    status = null,
                    insertionId = it,
                    userId = it1
                )
            }
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