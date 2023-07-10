package com.example.vintedandroid.view

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vintedandroid.client.apis.AuthApi
import com.example.vintedandroid.client.infrastructure.ResponseType
import com.example.vintedandroid.client.models.LoginUserDto

@Composable
fun LoginScreen(navController: NavHostController) {
    // Create a mutable state to hold the current text value
    val emailField = remember { mutableStateOf(TextFieldValue()) }
    val passwordField = remember { mutableStateOf(TextFieldValue()) }

    val auth = AuthApi()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center) {

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

            TextField(
                value = emailField.value,
                onValueChange = { emailField.value = it },
                modifier = Modifier.padding(8.dp),
                textStyle = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                label = { Text("Insert Email") }
            )
            TextField(
                value = passwordField.value,
                onValueChange = { passwordField.value = it },
                modifier = Modifier.padding(8.dp),
                textStyle = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                label = { Text("Insert Password") }
            )



            var loginResultText = ""

            Button(
                onClick = {
                    val loginUserDto = LoginUserDto(
                        email = emailField.value.text,
                        password = passwordField.value.text
                    )

                    val response = auth.login(loginUserDto)

                    //Log.i("tag", response.toString()) //response.contains("").toString()

                    navController.popBackStack()
                    navController.navigate("home")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Login")
            }


            Button(
                onClick = { navController.popBackStack(); navController.navigate("register") },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Need new account? Register!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}