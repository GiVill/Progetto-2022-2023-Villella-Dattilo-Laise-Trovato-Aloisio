package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.swagger.client.apis.ChatApi
import com.example.vintedandroid.swagger.client.models.ChatDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ChatViewModel(application: Application, userViewModel: UserViewModel) : ViewModel(){
    private val application = application
    private val userViewModel = userViewModel

    fun getAllChat():  Flow<Array<ChatDto>>?{
        //TODO Da fare. Bisogna prendere l'id dell'utente loggato ed inviarlo all'endpoint ChatApi().allChatUser(id)
        return null
    }
}