package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.swagger.client.apis.UserApi
import com.example.vintedandroid.swagger.client.models.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) :ViewModel() {

    private val application = application

    private val nullUserDatabaseDto = UserDatabaseDto(
        id = null,
        nickName = "",
        firstName = "",
        lastName = null,
        email = null,
        password = null,
        imageName = null,
        addressStreet = null,
        addressNumber = null,
        addressCity = null,
        addressCap = null,
        addressState = null,
        addressRegion = null,
        accessToken = null,
        refreshToken = null
    )

    fun getAllUserFromRoomDatabase(): Flow<UserDatabaseDto?> {
        Log.i("UserViewModel::class", "getAllUserFromRoomDatabase()")
        return AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getSingleUserFlow()
    }

    fun getUserById(id: Long): UserDto {
        return UserApi().getUserDtoById(id)
    }

    fun deleteUser(user : UserDatabaseDto){
        Log.i("UserViewModel::class", "deleteUser()")
        LoggedUserDetails.getInstance().setCurrentUser(nullUserDatabaseDto)
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().delete(user)
        }
    }
}