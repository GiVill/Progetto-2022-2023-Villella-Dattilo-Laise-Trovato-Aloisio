package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vintedandroid.R
import com.example.vintedandroid.model.application_status.internetChecker

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun noConnectionActivity(application :Context){
    Scaffold(){
        Box(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            contentAlignment = Alignment.Center ) {

            Card(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text(text = stringResource(R.string.connect_to_internet)) //, fontSize = 32.sp
                Spacer(modifier = Modifier.width(3.dp))
                /*
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "No Internet Connection",
                    //tint = Color.Red
                )
                 */
                Button(onClick = {
                    if (internetChecker(application)){
                        Log.i("NoConnectionScreen::class","You are now connected")
                        //Forse andrebbe fatto un navigate da qualche parte. (alla home?)
                        //navController.popBackStack(); navController.navigate(ScreenController.Home.route)
                    }
                    else{ Log.i("NoConnectionScreen::class","You are still disconnected!")  }
                }, modifier = Modifier.padding(8.dp)) {
                    Text(text = stringResource(R.string.retry_connection)+"(Per ora non fa nulla)")
                }
            }
        }
        Toast.makeText(application.applicationContext, stringResource(R.string.not_connected), Toast.LENGTH_SHORT).show()
    }
}