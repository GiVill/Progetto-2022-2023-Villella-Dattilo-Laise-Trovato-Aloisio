package com.example.vintedandroid.view

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun noConnectionScreen(application :Context){
    Scaffold(){
        Box(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            contentAlignment = Alignment.Center ) {

            Card(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text(text = "Please connect to the internet") //, fontSize = 32.sp
                Spacer(modifier = Modifier.width(3.dp))
                /*
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "No Internet Connection",
                    //tint = Color.Red
                )
                 */
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(8.dp)) {
                    Text(text = "Retry")
                }
            }
        }
        Toast.makeText(application.applicationContext, "You are not connected to the Internet!", Toast.LENGTH_SHORT).show()
    }
}