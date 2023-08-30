package com.example.vintedandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import kotlinx.coroutines.flow.Flow

class UserViewModel(application: Application) :ViewModel() {

    private val application = application

    //TODO: NON è  STATO TESTATO
    fun getAllUserFromRoomDatabase(): Flow<UserDatabaseDto> {
        return AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()

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

}