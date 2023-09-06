package com.example.vintedandroid.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.swagger.client.apis.ChatMessageApi
import com.example.vintedandroid.swagger.client.models.ChatMessage
import com.example.vintedandroid.swagger.client.models.NewMessageDto
import kotlinx.coroutines.flow.Flow

class ChatMessageViewModel(context: Context,) : ViewModel() {

    fun getAllMessages(chatId: String?):  Array<ChatMessage>{
        val Messages =  ChatMessageApi().allChatMessage(chatId);
        return Messages
    }

    fun sendMessage(newMessageText: String, chatId: Long, receiver: Long) {
        val newMessageDto = NewMessageDto(
            sender =  LoggedUserDetails.getInstance().getCurrentUser().id,
            reciver = receiver,
            message = newMessageText,
            chatId = chatId
        )

        try {
            ChatMessageApi().insertMessage(newMessageDto)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}