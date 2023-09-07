package com.example.vintedandroid.view.config

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vintedandroid.R

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
        placeholder = { Text(text = stringResource(R.string.type_your)+" $name") }
    )
}

@Composable
fun createPersonalizedTextfieldPassword(textField: MutableState<TextFieldValue>, onValidationStatusChange: (Boolean) -> Unit){

    var showPassword by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(true) }

    //var passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex()

    var passwordRegex = ".{8,}".toRegex()

    val focusRequester = remember { FocusRequester() }

    TextField(
        value = textField.value,
        onValueChange = { newText ->
            textField.value = newText
            isValid = passwordRegex.matches(textField.value.text)
            onValidationStatusChange(isValid)
            //isErrorState.value = !passwordRegex.matches(newText.text)
            //onValidationStatusChange(passwordRegex.matches(newText.text))
        },
        label = { Text(text = stringResource(R.string.password)) },
        placeholder = { Text(text = stringResource(R.string.enter_your_password)) },
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
                color = if (textField.value.text.isNotEmpty() && !isValid) Color.Red else Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done // Set the keyboard action to "Done"
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // When "Done" action is triggered (e.g., user presses "Done" on keyboard)
                // Perform the validation and inform the caller
                Log.i("A normale", "$isValid, $onValidationStatusChange")

                isValid = passwordRegex.matches(textField.value.text)
                onValidationStatusChange(isValid)

                Log.i("AA", "$isValid, $onValidationStatusChange")

                // Clear focus to dismiss the keyboard
                focusRequester.freeFocus()
            }
        )
    )
}


@Composable
fun createPersonalizedTextfieldPasswordWithSpecifiedRegex(textField: MutableState<TextFieldValue>, passwordRegex: Regex, onValidationStatusChange: (Boolean) -> Unit){

    var showPassword by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(true) }

    //var passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex()

    //var passwordRegex = ".{8,}".toRegex()

    val focusRequester = remember { FocusRequester() }

    TextField(
        value = textField.value,
        onValueChange = { newText ->
            textField.value = newText
            isValid = passwordRegex.matches(textField.value.text)
            onValidationStatusChange(isValid)
            //isErrorState.value = !passwordRegex.matches(newText.text)
            //onValidationStatusChange(passwordRegex.matches(newText.text))
        },
        label = { Text(text = stringResource(R.string.password)) },
        placeholder = { Text(text = stringResource(R.string.enter_your_password)) },
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
                color = if (textField.value.text.isNotEmpty() && !isValid) Color.Red else Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done // Set the keyboard action to "Done"
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // When "Done" action is triggered (e.g., user presses "Done" on keyboard)
                // Perform the validation and inform the caller
                isValid = passwordRegex.matches(textField.value.text)
                onValidationStatusChange(isValid)

                // Clear focus to dismiss the keyboard
                focusRequester.freeFocus()
            }
        )
    )
}

@Composable
fun createCheckbox(checkStatus: MutableState<Boolean>) {

    Row(
        modifier = Modifier
            .padding(2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        //verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Checkbox(
            checked = checkStatus.value,
            onCheckedChange = { isChecked ->
                checkStatus.value = isChecked
            },
            modifier = Modifier.padding(2.dp),
            colors = CheckboxDefaults.colors(checkedColor = Color.Green),

        )
        //Spacer(modifier = Modifier.width(2.dp))
        Text(text = "Do you want to make it private?")
    }
}


const val fixedUrlForImage = "https://192.168.1.90:8010/vintedProject-api/v1/images/"
const val logTag = "ImageConfiguration::class"

@Composable
fun PersonalizedAsyncImage(imageName: String?, subject: String) {

    Log.i(logTag, "url: $fixedUrlForImage$imageName")

    AsyncImage(
        model = "$fixedUrlForImage$imageName",
        contentDescription = "Async Image Of The $subject",
        contentScale = ContentScale.Crop
    )
}
