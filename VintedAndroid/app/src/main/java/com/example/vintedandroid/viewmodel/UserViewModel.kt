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

    //TODO: NON è  STATO TESTATO
    fun getAllUserFromRoomDatabase(): UserDatabaseDto? {

        var user :UserDatabaseDto? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getSingleUser()
        }
        Log.i("UserViewModel::class", "User from is: $user")
        return user

        /*
        LaunchedEffect(Unit) {
        if (userFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()
            }
            userFromDB.clear()
            if(databaseItems.isNotEmpty()) {
                userFromDB.addAll(databaseItems)
            }
        }
    }
        */
    }

    //TODO Da testare
    fun deleteUser(user : UserDatabaseDto){
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().delete(user)
        }
    }
}