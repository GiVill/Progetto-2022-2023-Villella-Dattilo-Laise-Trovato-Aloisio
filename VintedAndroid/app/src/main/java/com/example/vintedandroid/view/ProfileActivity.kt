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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.vintedandroid.view.config.PersonalizedAsyncImage
import com.example.vintedandroid.view.config.createPersonalizedTextfieldPassword
import com.example.vintedandroid.viewmodel.UserViewModel

@Composable
fun ProfileActivity(userViewModel: UserViewModel, navController: NavHostController) {

    val userFromDB: State<UserDatabaseDto?> = userViewModel.getAllUserFromRoomDatabase().collectAsState(initial = null)

    Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.profile),
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

                    Button(
                        onClick = {
                            navController.navigate(ScreenController.UpdatePassword.route)
                                  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = stringResource(R.string.change_password))
                    }
                }
        }
    }
}

@Composable
private fun userDetail(userFromDB: State<UserDatabaseDto?>){

    Card {
        if (userFromDB.value?.imageName != null) {

            PersonalizedAsyncImage(imageName = userFromDB.value?.imageName.toString(), subject = "ProfileActivity::class")

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
                    Text(text = stringResource(R.string.nickname))
                    Divider()
                    Text(text = stringResource(R.string.firstname))
                    Divider()
                    Text(text = stringResource(R.string.lastname))
                    Divider()
                    Text(text = stringResource(R.string.email))
                    Divider()
                    Text(text = stringResource(R.string.address))
                }
                Column(
                    Modifier
                        .padding(10.dp)
                        .weight(1f)
                ) {
                    Log.i("ProfileActivity::class", "email ${userFromDB.value?.email}")
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
