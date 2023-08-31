package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vintedandroid.view.config.createPersonalizedTextfield
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPassword
import com.example.vintedandroid.viewmodel.LoginRegistrationViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun RegistrationScreen(navController: NavHostController, application: Context, loginRegistrationViewModel: LoginRegistrationViewModel) {

    val emailField = remember { mutableStateOf(TextFieldValue()) }
    val nicknameField = remember { mutableStateOf(TextFieldValue()) }
    val firstnameField = remember { mutableStateOf(TextFieldValue()) }
    val lastnameField = remember { mutableStateOf(TextFieldValue()) }
    val addressCapField = remember { mutableStateOf(TextFieldValue()) }
    val addressCityField = remember { mutableStateOf(TextFieldValue()) }
    val addressNumberField = remember { mutableStateOf(TextFieldValue()) }
    val addressStreetField = remember { mutableStateOf(TextFieldValue()) }

    var passwordField = remember { mutableStateOf(TextFieldValue()) }

    var loginUnsuccessful = remember {mutableStateOf(false)}


    var buttonEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "REGISTRATION", fontSize = 36.sp)
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            if (loginUnsuccessful.value) {
                Log.i("LoginScreen:class", "Login was unsuccessful!")
                passwordField = remember { mutableStateOf(TextFieldValue()) }
                Text(
                    text = "Login failed. Please try again.",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red,
                )
            }
            createPersonalizedTextfield(textField = emailField, name = "Email", Icons.Default.Email)
            createPersonalizedTextfield(textField = nicknameField, name = "Nickname", Icons.Default.Person)
            createPersonalizedTextfield(textField = firstnameField, name = "Firstname", Icons.Default.Person)
            createPersonalizedTextfield(textField = lastnameField, name = "Lastname", Icons.Default.Person)

            createPersonalizedTextfield(textField = addressCapField, name = "Address Cap", Icons.Default.Numbers)
            createPersonalizedTextfield(textField = addressCityField, name = "Address City", Icons.Default.LocationCity)
            createPersonalizedTextfield(textField = addressNumberField, name = "Address Number", Icons.Default.Numbers)
            createPersonalizedTextfield(textField = addressStreetField, name = "Address Street", Icons.Default.Streetview)

            createPersonalizedTextfieldPassword(textField = passwordField)

            Button(
                onClick = {
                    buttonEnabled = false
                    loginUnsuccessful.value = false

                    if(loginRegistrationViewModel.registration(emailField.value.text, passwordField.value.text, firstnameField.value.text, lastnameField.value.text, nicknameField.value.text, addressCapField.value.text.toInt(), addressCityField.value.text, addressNumberField.value.text.toInt(), addressStreetField.value.text)){
                        navController.popBackStack()
                        navController.navigate(ScreenController.Home.route)
                    }
                    else{
                        loginUnsuccessful.value = true
                        buttonEnabled = true
                    }
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonEnabled
            ) { Text("Register") }

            Button(
                onClick = { //navController.popBackStack();
                    navController.navigate(ScreenController.Login.route) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Already have an account? Go to Login page")
            }
        }
    }
}
