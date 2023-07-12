package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vintedandroid.client.apis.AuthApi
import com.example.vintedandroid.client.models.LoginUserDto
import com.example.vintedandroid.client.models.NewUserDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.view.config.createPersonalizedTextfield
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("SuspiciousIndentation")
@Composable
fun RegistrationScreen(navController: NavHostController, application: Context) {

    val emailField = remember { mutableStateOf(TextFieldValue()) }
    val nicknameField = remember { mutableStateOf(TextFieldValue()) }
    val firstnameField = remember { mutableStateOf(TextFieldValue()) }
    val passwordField = remember { mutableStateOf(TextFieldValue()) }

    var loginUnsuccessful by remember {mutableStateOf(false)}
    var buttonEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "REGISTRATION", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(50.dp))

        createPersonalizedTextfield(textField = emailField, name = "Email")
        createPersonalizedTextfield(textField = nicknameField, name = "Nickname")
        createPersonalizedTextfield(textField = firstnameField, name = "Firstname")
        createPersonalizedTextfieldPassword(textField = passwordField)

        /*
        TextField(
            value = passwordField.value,
            onValueChange = { passwordField.value = it },
            modifier = Modifier.padding(8.dp),
            textStyle = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
            label = { Text("Insert Password") }
        )*/

        Button(
            onClick = {
                buttonEnabled = false
                val registrationUserDto = NewUserDto(
                    password = passwordField.value.text,
                    firstName = firstnameField.value.text,
                    nickName = nicknameField.value.text,
                    email = emailField.value.text
                )
                    CoroutineScope(Dispatchers.IO).launch {
                        val auth = AuthApi()

                        val t = auth.signUp(registrationUserDto)
                        withContext(Dispatchers.Main) {
                            if (t.access_token != null) {

                                val registratedUser = t.userDto?.let {
                                    UserDatabaseDto(
                                        nickName = it.nickName,
                                        firstName = t.userDto.firstName,
                                        lastName = t.userDto.lastName,
                                        password = null,
                                        imageName= t.userDto.imageName,
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

                                if (registratedUser != null) {
                                    AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().insert(registratedUser)

                                    navController.popBackStack()
                                    navController.navigate("home")
                                }
                            }
                            else{
                                loginUnsuccessful = true
                                buttonEnabled = true
                            }
                        }
                    }

                navController.popBackStack(); navController.navigate("home")
                      },
            modifier = Modifier.padding(8.dp),
            enabled = buttonEnabled
        ) { Text("Register") }

        Button(
            onClick = { navController.popBackStack(); navController.navigate("login") },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Already have an account? Go to Login page")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    val navController = rememberNavController()
    //RegistrationScreen(navController)
}