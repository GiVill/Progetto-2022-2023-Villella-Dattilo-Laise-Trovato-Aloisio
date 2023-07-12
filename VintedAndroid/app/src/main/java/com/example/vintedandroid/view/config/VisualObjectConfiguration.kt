package com.example.vintedandroid.view.config

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun createPersonalizedTextfield(textField: MutableState<TextFieldValue>,name: String){

    val isFieldSelected = remember { mutableStateOf(false) }

    TextField(
        value = textField.value,
        onValueChange = { input ->
            textField.value = input
        },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "$name Icon") },
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

@Composable
fun createPersonalizedTextfieldPassword(textField: MutableState<TextFieldValue>){

    var showPassword by remember { mutableStateOf(false) }

    TextField(
        value = textField.value,
        onValueChange = { newText ->
            textField.value = newText
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
                    imageVector = if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    )
}

