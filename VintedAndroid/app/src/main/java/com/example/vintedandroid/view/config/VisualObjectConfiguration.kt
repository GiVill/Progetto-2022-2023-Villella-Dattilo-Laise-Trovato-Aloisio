package com.example.vintedandroid.view.config

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
/*
@Composable
fun createPersonalizedTextfield(textField: MutableState<TextFieldValue>, name: String, icon: ImageVector){

    //L'ImageVector dovrebbe essere qualcosa del tipo --> Icons.Default.Email

    val isFieldSelected = remember { mutableStateOf(false) }

    TextField(
        value = textField.value,
        onValueChange = { input ->
            textField.value = input
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = "$name Icon") },
        trailingIcon = {
            if(isFieldSelected.value){
                IconButton(
                    onClick ={textField.value = TextFieldValue("") }
                ) { Icon( imageVector = Icons.Default.Close, contentDescription = "$name Icon") } } },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .onFocusChanged { focusState ->
                isFieldSelected.value = focusState.isFocused
            },
        label = { Text(text = "$name") },
        placeholder = { Text(text = "Type your $name") }
    )
}
*/

val emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9]+\\.)+[A-Za-z]{2,4}\$".toRegex()
val nameRegex = "^[A-Za-z\\s]{2,}\$".toRegex()
val nicknameRegex = "^[A-Za-z0-9_-]{3,}\$".toRegex()
val addressCapRegex = "^\\d{5}\$".toRegex()
val addressCityRegex = "^[A-Za-z\\s]{2,}\$".toRegex()
val addressNumberRegex = "^\\d+\$".toRegex()
val addressStreetRegex = "^[A-Za-z0-9\\s]{2,}\$".toRegex()

@Composable
fun createPersonalizedTextfield(
    textField: MutableState<TextFieldValue>,
    name: String,
    icon: ImageVector,
    regexPattern: Regex
) {
    val isFieldSelected = remember { mutableStateOf(false) }

    TextField(
        value = textField.value,
        onValueChange = { input ->
            textField.value = input
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = "$name Icon") },
        trailingIcon = {
            if (isFieldSelected.value && textField.value.text.isNotEmpty()) {
                IconButton(
                    onClick = { textField.value = TextFieldValue("") }
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "$name Icon")
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                width = 1.dp,
                color = if (!isFieldSelected.value && textField.value.text.isNotEmpty() && !regexPattern.matches(textField.value.text)) Color.Red else Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            .onFocusChanged { focusState ->
                isFieldSelected.value = focusState.isFocused
            },
        label = { Text(text = "$name") },
        placeholder = { Text(text = "Type your $name") }
    )
}




@Composable
fun createPersonalizedTextfieldPassword(textField: MutableState<TextFieldValue>){

    var showPassword by remember { mutableStateOf(false) }
    val isErrorState = remember { mutableStateOf(false) }

    var passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex()

    TextField(
        value = textField.value,
        onValueChange = { newText ->
            textField.value = newText
            isErrorState.value = !passwordRegex.matches(newText.text)
        },
        label = { Text(text = "Password") },
        placeholder = { Text(text = "Enter your password") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    imageVector = if (showPassword) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
            width = 1.dp,
            color = if (isErrorState.value) Color.Red else Color.Transparent,
            shape = RoundedCornerShape(4.dp)
        )
    )
}

