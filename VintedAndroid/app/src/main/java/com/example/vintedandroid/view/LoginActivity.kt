package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.Text
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.example.vintedandroid.R
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.view.config.createPersonalizedTextfield
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPassword
import com.example.vintedandroid.viewmodel.LoginRegistrationViewModel
import com.example.vintedandroid.viewmodel.UserViewModel


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginActivity(navController: NavHostController, loginRegistrationViewModel: LoginRegistrationViewModel, application: Context) {

    val emailField = remember { mutableStateOf(TextFieldValue()) }
    val emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9]+\\.)+[A-Za-z]{2,4}\$".toRegex()
    var passwordField = remember { mutableStateOf(TextFieldValue()) }
    var isValid by remember { mutableStateOf(false) }

    //var userFromDB1 = userViewModel.getAllUserFromRoomDatabase()

    var loginUnsuccessful = remember {mutableStateOf(false)}


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center) {

        if(LoggedUserDetails.getInstance().getCurrentUser().id == null){
            Log.i("LoginActivity", "No user is logged")
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
                        Log.i("LoginScreen:class", "Login was unsuccessful!")
                        passwordField = remember { mutableStateOf(TextFieldValue()) }
                        Text(
                            text = stringResource(R.string.login_failed),
                            modifier = Modifier.padding(16.dp),
                            color = Color.Red,
                        )
                    }

                    createPersonalizedTextfield(textField = emailField, name = stringResource(R.string.email), icon = Icons.Default.Email, emailRegex)
                    createPersonalizedTextfieldPassword(textField = passwordField){ regexValidation ->
                        isValid = regexValidation
                    }

                    loginButton(navController = navController, email = emailField.value.text, password = passwordField.value.text, loginUnsuccessful = loginUnsuccessful, loginRegistrationViewModel = loginRegistrationViewModel, isValid = isValid, application = application)
                    goToRegistrationButton(navController = navController)

                }
    }else{
        Log.i("LoginActivity", "A user is logged")
            navController.popBackStack()
            navController.navigate(ScreenController.Home.route)
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
private fun loginButton(navController: NavHostController, email: String, password: String, loginUnsuccessful: MutableState<Boolean>, loginRegistrationViewModel: LoginRegistrationViewModel, isValid: Boolean, application: Context){

    var buttonEnabled by remember { mutableStateOf(true) }
    Button(
        onClick = {
            buttonEnabled = false
            loginUnsuccessful.value = false
            if(isValid) {
                if (loginRegistrationViewModel.login(email, password)) {
                    navController.popBackStack()
                    navController.navigate(ScreenController.Home.route)
                } else {
                    loginUnsuccessful.value = true
                    buttonEnabled = true
                }
            }else{
                buttonEnabled = true
                Toast.makeText(application.applicationContext, "The Password is Too Short!", Toast.LENGTH_SHORT).show()
                Log.i("Login", "The Password is Too Short!")
            }
        },
        modifier = Modifier.padding(8.dp),
        enabled = buttonEnabled
    ) { Text(stringResource(R.string.login)) }
}



@Composable
private fun goToRegistrationButton(navController: NavHostController){
    Button(
        onClick = { navController.navigate(ScreenController.Register.route) },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(stringResource(R.string.sign_up))
    }
}


