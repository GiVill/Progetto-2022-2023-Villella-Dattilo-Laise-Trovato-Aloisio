package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vintedandroid.client.apis.AuthApi
import com.example.vintedandroid.client.models.LoginUserDto
import com.example.vintedandroid.client.models.TokenResponse
import com.example.vintedandroid.model.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.view.config.createPersonalizedTextfield
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPassword


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(navController: NavHostController, application: Context) {

    val emailField = remember { mutableStateOf(TextFieldValue()) }
    var passwordField = remember { mutableStateOf(TextFieldValue()) }

    val userFromDB = remember { mutableStateListOf<UserDatabaseDto>() }
    var isLoaded = remember { mutableStateOf(false) }

    var loginUnsuccessful = remember {mutableStateOf(false)}

    //var test by remember {mutableStateOf(false)}


    LaunchedEffect(Unit) {
        if (userFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()
            }
            userFromDB.clear()
            if(databaseItems.isNotEmpty()){
                userFromDB.addAll(databaseItems)
            }
            isLoaded.value = true
        }
    }


    //checkIfUserInDB(userFromDB = userFromDB, application = application, isLoaded = isLoaded)

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center) {

        if(isLoaded.value){
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //if(userFromDB.isEmpty() ) { //&& !test

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "LOGIN", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(50.dp))

                    if (loginUnsuccessful.value) {
                        passwordField = remember { mutableStateOf(TextFieldValue()) }
                        Text(
                            text = "Login failed. Please try again.",
                            modifier = Modifier.padding(16.dp),
                            color = Color.Red,

                        )
                    }

                    createPersonalizedTextfield(textField = emailField, name = "Email", icon = Icons.Default.Email)
                    createPersonalizedTextfieldPassword(textField = passwordField)

                    loginButton(navController = navController, application = application, email = emailField.value.text, password = passwordField.value.text, loginUnsuccessful = loginUnsuccessful)
                    goToRegistrationButton(navController = navController)

                }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //}else{
                //test = true
                //navController.popBackStack()
                //navController.navigate(ScreenController.Home.route)
            //}
        }
    }
}

private fun checkIfUserInDB(userFromDB: SnapshotStateList<UserDatabaseDto>, application: Context, isLoaded: MutableState<Boolean>) {
    CoroutineScope(Dispatchers.IO).launch {
        if (userFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()
            }
            userFromDB.clear()
            if(databaseItems.isNotEmpty()){
                userFromDB.addAll(databaseItems)
            }
            isLoaded.value = true
        }
    }
}

//TODO Controllare che funzioni tutto correttamente
@Composable
private fun loginButton(navController: NavHostController, application: Context, email: String, password: String, loginUnsuccessful: MutableState<Boolean>){

    var buttonEnabled by remember { mutableStateOf(true) }
    val auth = AuthApi()

    Button(
        onClick = {
            buttonEnabled = false
            val loginUserDto = convertLoginUserDTO(email, password)
            loginUnsuccessful.value = false
            CoroutineScope(Dispatchers.IO).launch {

                val t = auth.login(loginUserDto)

                withContext(Dispatchers.Main) {
                    if (t.access_token != null) {
                        val loggedUser = convertUserDTOtoUserDB(t)
                        if (loggedUser != null) {
                            AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().insert(loggedUser)

                            navController.popBackStack()
                            navController.navigate(ScreenController.Home.route)
                        }

                    } else {
                        loginUnsuccessful.value = true
                        buttonEnabled = true
                    }
                }
            }

            //Log.i("tag", response.toString()) //response.contains("").toString()
        },
        modifier = Modifier.padding(8.dp),
        enabled = buttonEnabled
    ) { Text("Login") }
}

@Composable
private fun goToRegistrationButton(navController: NavHostController){
    Button(
        onClick = { navController.popBackStack(); navController.navigate("register") },
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Need new account? Register!")
    }
}

private fun convertLoginUserDTO(email: String, password: String): LoginUserDto{
    return LoginUserDto(
        email = email,
        password = password
    )
}

private fun convertUserDTOtoUserDB(t: TokenResponse) :UserDatabaseDto?{

    return t.userDto?.let {
        UserDatabaseDto(
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
