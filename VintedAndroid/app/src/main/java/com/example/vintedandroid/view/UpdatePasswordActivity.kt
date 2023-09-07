package com.example.vintedandroid.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vintedandroid.R
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPassword
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPasswordWithSpecifiedRegex
import com.example.vintedandroid.viewmodel.UpdatePasswordViewModel

@Composable
fun UpdatePasswordActivity(updatePasswordViewModel: UpdatePasswordViewModel, application: Context) {

    var passwordField = remember { mutableStateOf(TextFieldValue()) }
    var passwordField2 = remember { mutableStateOf(TextFieldValue()) }
    var passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d).{8,}\$".toRegex()

    var isValid by remember { mutableStateOf(false) }
    var isValidSecond by remember { mutableStateOf(false) }


    var mismatchPassword = remember {mutableStateOf(false)}

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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(R.string.change_password), fontSize = 26.sp)
                if(mismatchPassword.value){
                    Log.i("UpdatePassword:class", "Update Password was unsuccessful!")
                    passwordField = remember { mutableStateOf(TextFieldValue()) }
                    Text(
                        text = stringResource(R.string.missmatch_password),
                        modifier = Modifier.padding(16.dp),
                        color = Color.Red,
                    )
                }
                Text(text = stringResource(R.string.enter_new_password))
                createPersonalizedTextfieldPasswordWithSpecifiedRegex(textField = passwordField, passwordRegex = passwordRegex){ regexValidation ->
                    isValid = regexValidation
                }
                Text(text = stringResource(R.string.type_password_again))
                createPersonalizedTextfieldPasswordWithSpecifiedRegex(textField = passwordField2, passwordRegex = passwordRegex){ regexValidation ->
                    isValidSecond = regexValidation
                }
                Button(onClick = {
                    mismatchPassword.value = false
                    if(isValid && isValidSecond) {
                        if (!updatePasswordViewModel.updatePassword(
                                passwordField.value.text,
                                passwordField2.value.text
                            )
                        ) {
                            mismatchPassword.value = true
                        }
                    }
                    else{
                        Toast.makeText(application.applicationContext, "The Password should be of at least 8 character, at least one letter and at least one digit", Toast.LENGTH_LONG).show()
                    }
                },
                    modifier = Modifier
                        .padding(8.dp)) {
                    Text(text = stringResource(R.string.send_new_password))
                }
            }
        }

    }
}