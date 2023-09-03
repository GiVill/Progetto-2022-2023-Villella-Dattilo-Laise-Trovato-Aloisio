package com.example.vintedandroid.model

import com.example.vintedandroid.model.dto.UserDatabaseDto

class LoggedUserDetails private constructor() {

    private var currentUser = UserDatabaseDto(
        id = null,
        nickName = "",
        firstName = "",
        lastName = null,
        email = null,
        password = null,
        imageName = null,
        //birthDate = null,
        //gender = null,
        addressStreet = null,
        addressNumber = null,
        addressCity = "",
        addressCap = null,
        addressState = null,
        addressRegion = null,
        accessToken = null
    )

    fun getCurrentUser() : UserDatabaseDto{
        return this.currentUser
    }

    fun setCurrentUser(currentUser : UserDatabaseDto){
        this.currentUser = currentUser
    }

    companion object {
        private var instance: LoggedUserDetails? = null

        fun getInstance(): LoggedUserDetails {
            return instance ?: LoggedUserDetails().also { instance = it }
        }
    }

}