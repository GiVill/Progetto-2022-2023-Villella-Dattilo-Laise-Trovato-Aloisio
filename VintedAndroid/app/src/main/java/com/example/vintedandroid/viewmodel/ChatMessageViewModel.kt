package com.example.vintedandroid.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.swagger.client.apis.ChatApi
import com.example.vintedandroid.swagger.client.apis.ChatMessageApi
import com.example.vintedandroid.swagger.client.models.ChatDto
import com.example.vintedandroid.swagger.client.models.ChatMessage
import com.example.vintedandroid.swagger.client.models.NewMessageDto

class ChatMessageViewModel(context: Context,) : ViewModel() {

    fun getAllMessages(chatId: Long):  Array<ChatMessage>?{
        val Messages =  ChatMessageApi().allChatMessage(chatId);
        return Messages
    }

    fun sendMessage(newMessageText: String, chatId: Long, reciver : Long) {
        val newMessageDto = NewMessageDto(
            sender =  LoggedUserDetails.getInstance().getCurrentUser().id,
            reciver = reciver,
            message = newMessageText,
            chatId = chatId
        )

         ChatMessageApi().insertMessage(newMessageDto);


    }

}