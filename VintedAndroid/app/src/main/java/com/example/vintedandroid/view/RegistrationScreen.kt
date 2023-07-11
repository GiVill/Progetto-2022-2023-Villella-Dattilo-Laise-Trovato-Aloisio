package com.example.vintedandroid.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vintedandroid.client.apis.AuthApi
import com.example.vintedandroid.client.models.LoginUserDto
import com.example.vintedandroid.client.models.NewUserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RegistrationScreen(navController: NavHostController) {
    // Create a mutable state to hold the current text value
    val emailField = remember { mutableStateOf(TextFieldValue()) }
    val nicknameField = remember { mutableStateOf(TextFieldValue()) }
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

        Text(text = "REGISTRATION(per ora non funziona, clicca su Register per continuare)", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(50.dp))

        TextField(
            value = emailField.value,
            onValueChange = { emailField.value = it },
            modifier = Modifier.padding(8.dp),
            textStyle = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
            label = { Text("Insert Email") }
        )
        TextField(
            value = nicknameField.value,
            onValueChange = { nicknameField.value = it },
            modifier = Modifier.padding(8.dp),
            textStyle = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
            label = { Text("Insert Nickname") }
        )
        TextField(
            value = passwordField.value,
            onValueChange = { passwordField.value = it },
            modifier = Modifier.padding(8.dp),
            textStyle = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
            label = { Text("Insert Password") }
        )

        Button(
            onClick = {
                    buttonEnabled = false
                val registrationUserDto = NewUserDto(
                    password = passwordField.value.text,
                    nickName = nicknameField.value.text,
                    email = emailField.value.text
                )
                    CoroutineScope(Dispatchers.IO).launch {
                        val auth = AuthApi()

                        val t = auth.signUp(registrationUserDto)
                        withContext(Dispatchers.Main) {
                            if (t.access_token != null) {
                                navController.popBackStack()
                                navController.navigate("home")
                            }
                            else{
                                loginUnsuccessful = true
                                buttonEnabled = true
                            }
                        }
                    }

                //Log.i("tag", response.toString()) //response.contains("").toString()
                navController.popBackStack(); navController.navigate("home")
                      },
            modifier = Modifier.padding(8.dp),
            enabled = buttonEnabled
        ) {


            /*
            val auth = AuthApi()
            if(auth.signUp(registrationUserDto).accessToken != null){ //TODO AGGIUSTARE
                //Text(text = "Login Effettuato!")
                navController.popBackStack(); navController.navigate("home")
            }
            else{
                Text(text = "Password o Email sbagliate")
            }

             */
            Text("Register")
        }

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
    RegistrationScreen(navController)
}