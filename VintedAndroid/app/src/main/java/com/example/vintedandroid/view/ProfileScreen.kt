package com.example.vintedandroid.view

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
//import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.theme.Typography
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.content.Context
import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.vintedandroid.view.config.ImageConfiguration

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, ExperimentalCoilApi::class)
@Composable
fun ProfileScreen(application: Context) {

    var isLoaded by remember { mutableStateOf(false) }
    var userFromDB = remember { mutableStateListOf<UserDatabaseDto>() }

    //Prende dal db gli/lo user e li salva nella variabile userFromDB.
    LaunchedEffect(Unit) {
        if (userFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()
            }
            userFromDB.clear()
            if(databaseItems.isNotEmpty()) {
                userFromDB.addAll(databaseItems)
            }
            isLoaded = true
        }
    }

    /*
    var isDropdownOpen by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Column {
        Button(
            onClick = { isDropdownOpen = !isDropdownOpen },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Show Dropdown")
        }

        if (isDropdownOpen) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
     */

    Column(modifier = Modifier.fillMaxSize()) {
        if(isLoaded) {
            Text(
                text = stringResource(R.string.account_settings),
                modifier = Modifier.padding(10.dp),
                style = TextStyle(fontSize = Typography.titleLarge.fontSize)
            )
            Column {
                Card {
                    if (userFromDB[0].imageName != null) {
                        //ImageConfiguration(painter = painter, imageScale = ContentScale.Crop)
                        ImageConfiguration(imageName = userFromDB[0].imageName.toString(), imageScale = ContentScale.Fit)
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
                                Text(text = "BirthDate:")
                                Divider()
                                Text(text = "Address:")
                            }
                            Column(
                                Modifier
                                    .padding(10.dp)
                                    .weight(1f)
                            ) {
                                Text(text = userFromDB[0].nickName)
                                Divider()
                                Text(text = userFromDB[0].firstName)
                                Divider()
                                userFromDB[0].lastName?.let { Text(text = it) }
                                Divider()
                                userFromDB[0].email?.let { Text(text = it) }
                                Divider()
                                userFromDB[0].birthDate?.let { Text(text = it) }
                                Divider()
                                Text(text = "${userFromDB[0].addressState} ${userFromDB[0].addressRegion} ${userFromDB[0].addressCity} ${userFromDB[0].addressCap} ${userFromDB[0].addressStreet} ${userFromDB[0].addressNumber}")
                            }
                        }
                    }

                }
                Divider()

                Spacer(modifier = Modifier.height(15.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                    modifyAccountInfoButton("Nickname")
                    modifyAccountInfoButton("Email")
                    modifyAccountInfoButton("Password")
                }

                Card(onClick = { /*TODO*/ }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(text = "Modify Email")
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = stringResource(R.string.default_account)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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