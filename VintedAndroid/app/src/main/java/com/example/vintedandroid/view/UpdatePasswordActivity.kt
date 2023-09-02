package com.example.vintedandroid.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPassword
import com.example.vintedandroid.viewmodel.UpdatePasswordViewModel

@Composable
fun UpdatePasswordActivity(updatePasswordViewModel: UpdatePasswordViewModel) {

    var passwordField = remember { mutableStateOf(TextFieldValue()) }
    var passwordField2 = remember { mutableStateOf(TextFieldValue()) }

    var loginUnsuccessful = remember {mutableStateOf(false)}


    //            loginUnsuccessful.value = false
    /*
    else {
                    loginUnsuccessful.value = true
                    buttonEnabled = true
                }
     */
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Card(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .align(Alignment.Center)) {
            Text(text = "Reset password", fontSize = 26.sp, modifier = Modifier.align(CenterHorizontally))
            if(loginUnsuccessful.value){
                Log.i("LoginScreen:class", "Login was unsuccessful!")
                passwordField = remember { mutableStateOf(TextFieldValue()) }
                Text(
                    text = "Password doesn't match. Please try again.",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red,
                )
            }
            Text(text = "Enter New Password", modifier = Modifier.align(CenterHorizontally))
            createPersonalizedTextfieldPassword(textField = passwordField)
            Text(text = "Retype New Password", modifier = Modifier.align(CenterHorizontally))
            createPersonalizedTextfieldPassword(textField = passwordField2)
            Button(onClick = {
                loginUnsuccessful.value = false
                if(!updatePasswordViewModel.updatePassword(passwordField.value.text, passwordField2.value.text)){
                    loginUnsuccessful.value = true
                    /*
                    if (loginUnsuccessful.value) {
                        Log.i("LoginScreen:class", "Login was unsuccessful!")
                        passwordField = remember { mutableStateOf(TextFieldValue()) }
                        Text(
                            text = "Login failed. Please try again.",
                            modifier = Modifier.padding(16.dp),
                            color = Color.Red,
                        )
                    }
                     */

                }
            },
            modifier = Modifier.align(CenterHorizontally).padding(8.dp)) {
                androidx.compose.material.Text(text = "Send New Password")
            }
        }
    }
    /*
    Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "LOGIN", fontSize = 48.sp)
     */
    
}