package com.example.vintedandroid.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class TokenViewModel(application: Context) : ViewModel() {

    private val application = application

    fun get(callback: (UserDatabaseDto?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val loggedUser = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getSingleUser()
            callback(loggedUser)
        }
    }


}