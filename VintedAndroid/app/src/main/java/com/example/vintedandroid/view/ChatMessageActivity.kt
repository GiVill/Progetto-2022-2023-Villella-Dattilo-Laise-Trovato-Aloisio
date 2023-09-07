package com.example.vintedandroid.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.swagger.client.models.ChatMessage
import com.example.vintedandroid.viewmodel.ChatMessageViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.vintedandroid.swagger.client.models.ChatDto
import kotlinx.coroutines.delay

@Composable
fun ChatMessageActivity(
    chatId: String?,
    chatMessageViewModel: ChatMessageViewModel,
    navController: NavHostController,
) {
    var reciver: Long = 0

    // Create a state to hold the list of messages
    val messagesState = remember { mutableStateOf<Array<ChatMessage>?>(null) }

    // Use LaunchedEffect to fetch messages every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            try {
                chatId?.let { chatId ->
                    val messages = chatMessageViewModel.getAllMessages(chatId)
                    messagesState.value = messages
                }
            } catch (e: Exception) {
                // Handle exceptions here if needed
            }

            // Delay for 3 seconds before fetching messages again
            delay(3000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 85.dp)
    ) {
        // Content goes here

        // Display messages using messagesState.value
        messagesState.value?.let { messages ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState(100000000))
                    .padding(8.dp)
            ) {

                messages?.forEach { message ->
                    val isMe = message.sender == LoggedUserDetails.getInstance().getCurrentUser().id
                    val backgroundColor = if (isMe) Color.DarkGray else Color.White
                    val textColor = if (isMe) Color.White else Color.Black

                    Card(
                        modifier = Modifier
                            .width(240.dp)
                            .padding(8.dp)
                            .align(if (isMe) Alignment.End else Alignment.Start),
                        backgroundColor = backgroundColor
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
                        ) {
                            if (!isMe) {
                                Text(
                                    message.message.toString(),
                                    style = TextStyle(color = textColor),
                                    textAlign = TextAlign.Start
                                )
                            }else {
                                Text(
                                    message.message ?: "",
                                    style = TextStyle(color = textColor),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }

                    if (message.sender != LoggedUserDetails.getInstance().getCurrentUser().id) {
                        reciver = message.sender!!
                    }


                }
            }
        }

    }



        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(8.dp)
        )
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        // TextField e IconButton fissi sul margine inferiore
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .align(Alignment.BottomCenter)
        ) {
            var newMessageText by remember { mutableStateOf("") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    value = newMessageText,
                    onValueChange = { newText ->
                        newMessageText = newText
                    },
                    placeholder = { Text("Scrivi un messaggio...") },
                    singleLine = true,
                    textStyle = TextStyle(color = Color.White)
                )

                IconButton(
                    onClick = {
                        // Qui gestisci l'invio del messaggio
                        if (newMessageText.isNotBlank()) {
                            if (chatId != null) {
                                chatMessageViewModel.sendMessage(
                                    newMessageText,
                                    chatId.toLong(),
                                    reciver
                                )
                            }
                            newMessageText = ""
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Invia"
                    )
                }
            }
        }
    }

}



