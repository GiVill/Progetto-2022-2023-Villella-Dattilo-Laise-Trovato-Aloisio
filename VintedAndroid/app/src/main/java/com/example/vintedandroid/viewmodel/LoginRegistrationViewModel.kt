package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.client.apis.AuthApi
import com.example.vintedandroid.client.models.LoginUserDto
import com.example.vintedandroid.client.models.NewUserDto
import com.example.vintedandroid.client.models.TokenResponse
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRegistrationViewModel(application: Application) : ViewModel() {

    private val application = application

    fun insertLoggedUserInDatabase(loggedUser: UserDatabaseDto){
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().insert(loggedUser)
        }
    }

    fun login(email: String, password: String): Boolean {
        val loginUserDto = convertLoginUserDTO(email, password)     //Convert Login in userDto
        val token = AuthApi().login(loginUserDto)                   //Make the call API
        if(token.access_token != null){                             //Check Token
            Log.i("LoginScreen::class", "Login successful")
            val loggedUser = convertUserDTOtoUserDB(token)          //Take the User from Token
            if (loggedUser != null) {                               //Check User
                insertLoggedUserInDatabase(loggedUser)              //Insert User in Room Database
                return true
            }
        }
        return false
    }

    fun registration(email: String, password: String, firstname: String, nickname: String): Boolean {
        val registrationUserDto = createNewUserDto(email, password, firstname, nickname)     //Convert Login in userDto
        val token = AuthApi().signUp(registrationUserDto)                   //Make the call API
        if(token.access_token != null){                             //Check Token
            Log.i("LoginScreen::class", "Login successful")
            val registeredUser = convertUserDTOtoUserDB(token)          //Take the User from Token
            if (registeredUser != null) {                               //Check User
                insertLoggedUserInDatabase(registeredUser)              //Insert User in Room Database
                return true
            }
        }
        return false
    }

    /*
    val registrationUserDto = createNewUserDto(passwordField.value.text, firstnameField.value.text, nicknameField.value.text, emailField.value.text)
                    CoroutineScope(Dispatchers.IO).launch {

                        val t = auth.signUp(registrationUserDto)
                        withContext(Dispatchers.Main) {
                            if (t.access_token != null) {

                                val registeredUser = convertUserDTOtoUserDB(t)

                                if (registeredUser != null) {
                                    AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().insert(registeredUser)

                                    navController.popBackStack()
                                    navController.navigate(ScreenController.Home.route)
                                }
                            }
                            else{
                                buttonEnabled = true
                            }
                        }
                    }
     */


    private fun convertLoginUserDTO(email: String, password: String): LoginUserDto{
        return LoginUserDto(
            email = email,
            password = password
        )
    }

    private fun convertUserDTOtoUserDB(t: TokenResponse) :UserDatabaseDto?{

        return t.userDto?.let {
            UserDatabaseDto(
                id = it.id,
                nickName = it.nickName,
                firstName = t.userDto.firstName,
                lastName = t.userDto.lastName,
                password = null,
                imageName = t.userDto.imageName,
                birthDate = t.userDto.birthDate,
                gender = t.userDto.gender,
                addressStreet = t.userDto.addressStreet,
                addressNumber = t.userDto.addressNumber,
                addressCity = t.userDto.addressCity,
                addressCap = t.userDto.addressCap,
                addressState = t.userDto.addressState,
                addressRegion = t.userDto.addressRegion,
                accessToken = t.access_token
            )
        }
    }

    private fun createNewUserDto(email: String, password: String, firstName: String, nickName: String): NewUserDto {
        return NewUserDto(
            password = password,
            firstName = firstName,
            nickName = nickName,
            email = email
        )
    }

}