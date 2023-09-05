package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.swagger.client.apis.ChatApi
import com.example.vintedandroid.swagger.client.models.ChatDto
import com.example.vintedandroid.swagger.client.models.ChatMessage

class ChatMessageViewModel(application: Application, userViewModel: UserViewModel) : ViewModel(){
    private val application = application
    private val userViewModel = userViewModel

    fun getAllChatMessage():  Array<ChatMessage>?{
        //TODO Prendere il chat Id dentro il componente padre e creare le api di chat message con swagger
        val chatMessage = LoggedUserDetails.getInstance().getCurrentUser().id?.let {
            ChatMessageApi().getAllChatMessage(
                ChatId
            )
        }
        Log.i("ChatViewModel::class", "allChatUser: $chats")
        return chatMessage
        //TODO Da fare. Bisogna prendere l'id dell'utente loggato ed inviarlo all'endpoint ChatApi().allChatUser(id)

    }
} {
}