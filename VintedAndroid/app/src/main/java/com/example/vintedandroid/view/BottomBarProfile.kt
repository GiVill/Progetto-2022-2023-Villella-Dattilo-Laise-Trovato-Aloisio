package com.example.vintedandroid.view


import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vintedandroid.R
import com.example.vintedandroid.client.models.UserDto
import com.example.vintedandroid.theme.Typography

//TODO: CAMBIARE USER CON INSERZIONE
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarProfile(navController: NavController, user : UserDto) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.account_settings),
            modifier = Modifier.padding(10.dp),
            style = TextStyle(fontSize = Typography.titleLarge.fontSize)
        )
        Column() {
            Card(onClick = { navController.popBackStack(); navController.navigate("profile") }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.AccountCircle,
                        contentDescription = stringResource(R.string.default_account),
                        modifier = Modifier
                            .padding(10.dp)
                            .size(size = 48.dp)
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = user.nickName)
                        Text(text = "Visualizza il mio profilo")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                }

            }

            Spacer(modifier = Modifier.height(15.dp))

            Card(onClick = { navController.popBackStack(); navController.navigate("favorite") }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Favorite,
                        contentDescription = stringResource(R.string.default_account),
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Text(text = "Preferiti")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                }
            }

            Card(onClick = { navController.popBackStack(); navController.navigate("balance") }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.PlayArrow,
                        contentDescription = stringResource(R.string.default_account),
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Text(text = "Saldo")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                }
            }

            Card(onClick = { navController.popBackStack(); navController.navigate("setting") }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.default_account),
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Text(text = "Impostazioni")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                }
            }

            Card(onClick = { navController.popBackStack(); navController.navigate("feedback") }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Info,
                        contentDescription = stringResource(R.string.default_account),
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Text(text = "INVIA FEEDBACK (PER IL MEME)")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                }
            }

            Card(onClick = { /*TODO*/ }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.ExitToApp,
                        contentDescription = stringResource(R.string.default_account),
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Text(text = "LogOut")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.default_account))
                }
            }
        }
    }
}

    /*
    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()){
        Column {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
                Icon(Icons.Filled.AccountCircle, contentDescription = stringResource(R.string.default_account))
            }
            Row {
                Text(text = "${user.lastName}  ${user.firstName}" )
            }
            Row {
                Text(text = user.nickName, textAlign = TextAlign.Center , style = TextStyle(fontSize = Typography.titleLarge.fontSize), modifier = Modifier.fillMaxWidth()  )
            }
        }

    }
     */

@Preview(showBackground = true)
@Composable
fun BottomBarProfilePreview() {

    val navController = rememberNavController()

    val user = UserDto(1L,"ciao","Boh","ciaoBoh")
    BottomBarProfile(navController, user)
}
