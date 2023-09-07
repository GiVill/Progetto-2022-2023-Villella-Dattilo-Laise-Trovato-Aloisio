package com.example.vintedandroid.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.vintedandroid.R
import com.example.vintedandroid.view.config.createPersonalizedTextfield

@Composable
fun FeedbackActivity(application : Context) {
    var messageField = remember { mutableStateOf(TextFieldValue()) }
    val messageRegex = "^\\s+\$".toRegex()
    Card(
        Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.feedback), fontSize = 32.sp)
            createPersonalizedTextfield(
                textField = messageField,
                name = stringResource(R.string.insert_message),
                icon = Icons.Default.Message,
                regexPattern = messageRegex
            )

            Button(
                onClick = {
                    messageField.value = TextFieldValue("")
                    Toast.makeText(application.applicationContext, "Message Sended", Toast.LENGTH_SHORT).show()

                }) {
                Text(stringResource(R.string.send_message))
            }
        }
    }
}