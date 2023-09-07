package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.model.dao.UserDatabaseDao_Impl
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.swagger.client.apis.ChatApi
import com.example.vintedandroid.swagger.client.apis.OfferApi
import com.example.vintedandroid.swagger.client.models.ChatDto
import androidx.compose.runtime.State
import com.example.vintedandroid.swagger.client.apis.ChatMessageApi
import com.example.vintedandroid.swagger.client.models.NewChatDto
import com.example.vintedandroid.swagger.client.models.NewMessageDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ChatViewModel() : ViewModel(){

    fun getAllChat():  Array<ChatDto>?{
        val chats = LoggedUserDetails.getInstance().getCurrentUser().id?.let {
            ChatApi().allChatUser(
                it
            )
        }
        Log.i("ChatViewModel::class", "allChatUser: $chats")
        return chats
    }


    fun NewChat(newChatDto: NewChatDto) {

        try {
            ChatApi().newChat(newChatDto)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}