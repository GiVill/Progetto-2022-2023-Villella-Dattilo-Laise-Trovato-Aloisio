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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.R
import com.example.vintedandroid.client.models.UserDto
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.theme.Typography
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(user : UserDto) {

    var isEditing by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf("") }

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
        Text(
            text = stringResource(R.string.account_settings),
            modifier = Modifier.padding(10.dp),
            style = TextStyle(fontSize = Typography.titleLarge.fontSize)
        )
        Column {
            Card(onClick = { /*TODO*/ },) {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = stringResource(R.string.default_account),
                    modifier = Modifier
                        .padding(10.dp)
                        .size(size = 48.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.align(CenterHorizontally)) {
                        Column(modifier = Modifier
                            .padding(10.dp)
                            .weight(1f)) {
                            Text(text = "Nickname:" )
                            Divider()
                            Text(text = "Firstname:" )
                            Divider()
                            Text(text = "Lastname:" )
                            Divider()
                            Text(text = "Email:" )
                            Divider()
                            Text(text = "BirthDate:" )
                            Divider()
                            Text(text = "Address:" )
                        }
                        Column(
                            Modifier
                                .padding(10.dp)
                                .weight(1f)) {
                            Text(text = user.nickName)
                            Divider()
                            Text(text = user.firstName )
                            Divider()
                            user.lastName?.let { Text(text = it) }
                            Divider()
                            user.email?.let { Text(text = it) }
                            Divider()
                            user.birthDate?.let { Text(text = it) }
                            Divider()
                            Text(text = "${user.addressState} ${user.addressRegion} ${user.addressCity} ${user.addressCap} ${user.addressStreet} ${user.addressNumber}")
                        }
                    }
                }

            }
            Divider()

            Spacer(modifier = Modifier.height(15.dp))

            Card(onClick = { isEditing = true }) {
                if (isEditing) {
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = textValue,
                        onValueChange = { newValue -> textValue = newValue },
                        modifier = Modifier.padding(10.dp)
                    )
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
                        Text(text = "Modifica nickname")
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                    }
                }
            }

            Card(onClick = { /*TODO*/ }) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)) {
                    Text(text = "Modifica email")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                }
            }

            Card(onClick = { /*TODO*/ }) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)) {
                    Text(text = "Modifica password")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val user = UserDto(UUID.randomUUID().toString(),"ciao","Boh","ciaoBoh","","ciao@yahoo.it",
        "10-05-2001",UserDto.Gender.MALE,"via napoli",8,"Lamezia",21312,
        "Italy","asdojad")
    ProfileScreen(user)
}