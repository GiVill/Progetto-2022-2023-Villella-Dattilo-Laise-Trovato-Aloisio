package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) :ViewModel() {

    private val application = application

    fun getAllUserFromRoomDatabase(): Flow<UserDatabaseDto?> {
        Log.i("UserViewModel::class", "getAllUserFromRoomDatabase()")
        return AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getSingleUserFlow()
    }

    //TODO Da testare
    fun deleteUser(user : UserDatabaseDto){
        Log.i("UserViewModel::class", "deleteUser()")
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().delete(user)
        }
    }
}