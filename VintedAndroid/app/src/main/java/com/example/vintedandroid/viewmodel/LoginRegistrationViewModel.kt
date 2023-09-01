package com.example.vintedandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.AuthApi
import com.example.vintedandroid.swagger.client.models.LoginUserDto
import com.example.vintedandroid.swagger.client.models.NewUserDto
import com.example.vintedandroid.swagger.client.models.TokenDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRegistrationViewModel(application: Application) : ViewModel() {

    private val application = application

    private fun insertLoggedUserInDatabase(loggedUser: UserDatabaseDto){
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().insert(loggedUser)
        }
    }

    fun login(email: String, password: String): Boolean {
        val loginUserDto = convertLoginUserDTO(email, password)     //Convert Login in userDto
        val token = AuthApi().login(loginUserDto)                   //Make the call API
        if(token.accessToken != null){                             //Check Token
            Log.i("LoginScreen::class", "Login successful")
            val loggedUser = convertUserDTOtoUserDB(token)          //Take the User from Token
            if (loggedUser != null) {                               //Check User
                insertLoggedUserInDatabase(loggedUser)              //Insert User in Room Database
                return true
            }
        }
        return false
    }

    fun registration(email: String, password: String, firstname: String, lastname: String, nickname: String, addressCap: Int, addressCity: String, addressNumber: Int, addressStreet: String): Boolean {

        if(checkEnteredText(email, password, firstname, lastname, nickname, addressCap, addressCity, addressNumber, addressStreet)) {

            val registrationUserDto = createNewUserDto(email, password, firstname, lastname, nickname, addressCap, addressCity, addressNumber, addressStreet)     //Convert Login in userDto
            val token = AuthApi().signUp(registrationUserDto)                   //Make the call API
            if (token.accessToken != null) {                                       //Check Token
                Log.i("LoginScreen::class", "Registration successful")
                val registeredUser =
                    convertUserDTOtoUserDB(token)          //Take the User from Token
                if (registeredUser != null) {                               //Check User
                    insertLoggedUserInDatabase(registeredUser)              //Insert User in Room Database
                    return true
                }
            }
        }
        return false
    }

    fun checkEnteredText(email: String, password: String, firstname: String, lastname: String, nickname: String, addressCap: Int, addressCity: String, addressNumber: Int, addressStreet: String): Boolean{
        val emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9]+\\.)+[A-Za-z]{2,4}\$"
        val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
        val nameRegex = "^[A-Za-z\\s]{2,}\$"
        val nicknameRegex = "^[A-Za-z0-9_-]{3,}\$"
        val addressCapRegex = "^\\d{5}\$"
        val addressCityRegex = "^[A-Za-z\\s]{2,}\$"
        val addressNumberRegex = "^\\d+\$"
        val addressStreetRegex = "^[A-Za-z0-9\\s]{2,}\$"

        val isEmailValid = email.matches(emailRegex.toRegex())
        val isPasswordValid = password.matches(passwordRegex.toRegex())
        val isFirstNameValid = firstname.matches(nameRegex.toRegex())
        val isLastNameValid = lastname.matches(nameRegex.toRegex())
        val isNicknameValid = nickname.matches(nicknameRegex.toRegex())
        val isAddressCapValid = addressCap.toString().matches(addressCapRegex.toRegex())
        val isAddressCityValid = addressCity.matches(addressCityRegex.toRegex())
        val isAddressNumberValid = addressNumber.toString().matches(addressNumberRegex.toRegex())
        val isAddressStreetValid = addressStreet.matches(addressStreetRegex.toRegex())

        if(isEmailValid && isPasswordValid && isFirstNameValid && isLastNameValid && isNicknameValid && isAddressCapValid && isAddressCityValid && isAddressNumberValid && isAddressStreetValid){
            Log.i("Login", "$isEmailValid , $email , ${email.matches(emailRegex.toRegex())}")
           return true
        }
        return false
    }


    private fun convertLoginUserDTO(email: String, password: String): LoginUserDto{
        return LoginUserDto(
            email = email,
            password = password
        )
    }

    private fun convertUserDTOtoUserDB(t: TokenDto) :UserDatabaseDto?{

        return t.userDto?.let {
            it.nickname?.let { it1 ->
                UserDatabaseDto(
                    id = it.id,
                    email = t.userDto.email,
                    nickName = it1,
                    firstName = t.userDto.firstName,
                    lastName = t.userDto.lastName,
                    password = null,
                    imageName = t.userDto.imageName,
                    //birthDate = t.userDto.birthDate,
                    //gender = t.userDto.gender,
                    addressStreet = t.userDto.addressStreet,
                    addressNumber = t.userDto.addressNumber,
                    addressCity = t.userDto.addressCity,
                    addressCap = t.userDto.addressCap,
                    addressState = t.userDto.addressState,
                    addressRegion = t.userDto.addressRegion,
                    accessToken = t.accessToken
                )
            }
        }
    }

    private fun createNewUserDto(email: String, password: String, firstName: String, lastname: String, nickname: String, addressCap: Int, addressCity: String, addressNumber: Int, addressStreet: String): NewUserDto {
        return NewUserDto(
            email = email,
            password = password,
            firstname = firstName,
            lastname = lastname,
            nickname = nickname,
            addressCap = addressCap,
            addressCity = addressCity,
            addressNumber = addressNumber,
            addressStreet = addressStreet
        )
    }

}