package com.example.vintedandroid.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.R
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.theme.Typography
import android.util.Log
import androidx.compose.material.Button
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import com.example.vintedandroid.view.config.ImageConfiguration
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPassword
import com.example.vintedandroid.viewmodel.UserViewModel

@Composable
fun ProfileActivity(userViewModel: UserViewModel) {

    val nicknameField = remember { mutableStateOf(TextFieldValue()) }
    val emailField = remember { mutableStateOf(TextFieldValue()) }
    val passwordField = remember { mutableStateOf(TextFieldValue()) }
    var isTextFieldVisible1 by remember { mutableStateOf(false) }

    val userFromDB: State<UserDatabaseDto?> = userViewModel.getAllUserFromRoomDatabase().collectAsState(initial = null)

    Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.account_settings),
                modifier = Modifier.padding(10.dp),
                style = TextStyle(fontSize = Typography.titleLarge.fontSize)
            )
            Column {

                userDetail(userFromDB)
                Divider()
                Spacer(modifier = Modifier.height(15.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
                {
                    //modifyAccountInfoButton("Nickname")
                    //modifyAccountInfoButton("Email")
                    //modifyAccountInfoButton("Password")


                    Button(
                        onClick = { isTextFieldVisible1 = !isTextFieldVisible1 },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = "Security & Password")
                    }

                    if (isTextFieldVisible1) {
                        //TODO modifica Immagine
                        createPersonalizedTextfieldPassword(textField = passwordField)

                        Button(
                            onClick = {
                                Log.i("ProfileScreen::class","Typed text: pppppppppppppppppppp")
                                // Perform the action when the send button is clicked
                                performSendAction()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Send")
                        }
                    }
                }
        }
    }
}

@Composable
private fun modifyAccountInfoButton(subject: String){

    var isTextFieldVisible by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    Card(modifier = Modifier.fillMaxWidth()) {//, onClick = {/*TODO*/}
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { isTextFieldVisible = !isTextFieldVisible },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Modify $subject")
            }

            if (isTextFieldVisible) {
                TextField(
                    value = value,
                    onValueChange = { newText ->
                        value = newText
                    },
                    label = { Text(text = "$subject") },
                    placeholder = { Text(text = "Type your new $subject") }
                )
                Button(
                    onClick = {
                        Log.i("ProfileScreen::class","Typed text: $value")
                        // Perform the action when the send button is clicked
                        performSendAction()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Send")
                }
            }
        }
    }
}

@Composable
private fun userDetail(userFromDB: State<UserDatabaseDto?>){

    Card {
        if (userFromDB.value?.imageName != null) {
            ImageConfiguration(
                imageName = userFromDB.value?.imageName.toString(),
                imageScale = ContentScale.Fit
            )
        } else {

            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.default_account),
                modifier = Modifier
                    .padding(10.dp)
                    .size(size = 48.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.align(CenterHorizontally)) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                ) {
                    Text(text = "Nickname:")
                    Divider()
                    Text(text = "Firstname:")
                    Divider()
                    Text(text = "Lastname:")
                    Divider()
                    Text(text = "Email:")
                    Divider()
                    Text(text = "Address:")
                }
                Column(
                    Modifier
                        .padding(10.dp)
                        .weight(1f)
                ) {
                    Log.i("ProfileScreen", "email ${userFromDB.value?.email}")
                    userFromDB.value?.let { Text(text = it.nickName) }
                    Divider()
                    userFromDB.value?.let { Text(text = it.firstName) }
                    Divider()
                    userFromDB.value?.lastName?.let { Text(text = it) }
                    Divider()
                    userFromDB.value?.email?.let { Text(text = it) }
                    Divider()
                    Text(text = "${userFromDB.value?.addressCity} ${userFromDB.value?.addressCap} ${userFromDB.value?.addressStreet} ${userFromDB.value?.addressNumber}")
                }
            }
        }
    }
}

private fun performSendAction() {
    // Add your logic here to handle the send action
}

/*
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val user = UserDto(UUID.randomUUID().toString(),"ciao","Boh","ciaoBoh","","ciao@yahoo.it",
        "10-05-2001",UserDto.Gender.MALE,"via napoli",8,"Lamezia",21312,
        "Italy","asdojad")
    //ProfileScreen(user)
}
 */