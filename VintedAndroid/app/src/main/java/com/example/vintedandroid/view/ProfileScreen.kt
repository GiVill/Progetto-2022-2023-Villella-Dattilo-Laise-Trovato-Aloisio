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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.R
import com.example.vintedandroid.client.models.UserDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.CartDto
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.theme.Typography
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, ExperimentalCoilApi::class)
@Composable
fun ProfileScreen(application: Context) {

    var isLoaded by remember { mutableStateOf(false) }
    var userFromDB = remember { mutableStateListOf<UserDatabaseDto>() }
    //var userFromDB by remember { mutableStateOf(UserDatabaseDto(UUID.randomUUID().toString(),"","",null,null,null,null,null,null,null,null,null,null,null,null)) }
    var url = remember { mutableStateOf("https://192.168.1.90:8010/vintedProject-api/v1/images/") }//: String? = null

    var isTextFieldVisible by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    //TODO
    //Prende dal db gli/lo user e li salva nella variabile userFromDB.
    LaunchedEffect(Unit) {
        if (userFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()
            }
            //itemsFromDB.clear()
            userFromDB.clear()
            if(databaseItems.isNotEmpty()) {
                userFromDB.addAll(databaseItems)
                url.value = "${url.value}${userFromDB[0].imageName.toString()}"
            }
            isLoaded = true
        }
    }

    val painter: ImagePainter = rememberImagePainter(url.value)

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
                        if (painter.state != ImagePainter.State.Empty) {
                            Image(
                                painter = painter,
                                contentDescription = null, // Provide a proper content description
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Text(text = "Errore nel caricare l'immagine")
                        }
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

                Card(modifier = Modifier.fillMaxWidth()) {

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = { isTextFieldVisible = !isTextFieldVisible },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Modify Nickname")
                        }

                        if (isTextFieldVisible) {
                            TextField(
                                value = value,
                                onValueChange = { newText ->
                                    value = newText
                                },
                                label = { Text(text = "Name") },
                                placeholder = { Text(text = "Type your name") }
                            )
                        }
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

                /*

            Card(onClick = { isEditing = true }) {
                if (isEditing) {
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = textValue,
                        onValueChange = { newValue -> textValue = newValue },
                        modifier = Modifier.padding(10.dp)
                    )
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(text = "Modifica nickname")
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = stringResource(R.string.default_account)
                        )
                    }
                }
            }

             */

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

                Card(onClick = { /*TODO*/ }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(text = "Modify Password")
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

private fun performSendAction() {
    // Add your logic here to handle the send action
}


//TODO DA TESTARE
@Composable
private fun testForLaunchEffect(userFromDB: SnapshotStateList<UserDatabaseDto>, application: Context, url: MutableState<String>, isLoaded : MutableState<Boolean>){
    //Prende dal db gli/lo user e li salva nella variabile userFromDB.
    LaunchedEffect(Unit) {
        if (userFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()
            }
            //itemsFromDB.clear()
            userFromDB.clear()
            userFromDB.addAll(databaseItems)
            url.value = "${url.value}${userFromDB[0].imageName.toString()}"
            isLoaded.value = true
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val user = UserDto(UUID.randomUUID().toString(),"ciao","Boh","ciaoBoh","","ciao@yahoo.it",
        "10-05-2001",UserDto.Gender.MALE,"via napoli",8,"Lamezia",21312,
        "Italy","asdojad")
    //ProfileScreen(user)
}