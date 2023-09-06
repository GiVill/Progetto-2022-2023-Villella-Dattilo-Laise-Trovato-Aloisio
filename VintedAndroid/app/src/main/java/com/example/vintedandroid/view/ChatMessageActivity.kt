package com.example.vintedandroid.view

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


@Composable
fun ChatMessageActivity(
    chatId: String?,
    chatMessageViewModel: ChatMessageViewModel,
    navController: NavHostController,
) {
    val messages: Array<ChatMessage>? =
        chatId?.let { chatMessageViewModel.getAllMessages(it) }

    var reciver: Long = 0;

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if (messages != null) {
            messages.forEach { message ->
                val isMe = message.sender == LoggedUserDetails.getInstance().getCurrentUser().id
                val backgroundColor = if (isMe) Color.DarkGray else Color.White
                val textColor = if (isMe) Color.White else Color.Black
                val alignment = if (isMe) Alignment.End else Alignment.Start
                if (message.sender != LoggedUserDetails.getInstance().getCurrentUser().id)
                    reciver = message.sender!!;

                Card(
                    modifier = Modifier
                        .width(240.dp)
                        .padding(8.dp)
                        .align(alignment),
                    backgroundColor = backgroundColor
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        if (!isMe) {
                            Text(
                                message.sender.toString(),
                                style = TextStyle(color = textColor),
                                textAlign = TextAlign.Start
                            )
                        }
                        Text(
                            message.message ?: "",
                            style = TextStyle(color = textColor),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        var newMessageText by remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
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








