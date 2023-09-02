package com.example.vintedandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.example.vintedandroid.swagger.client.models.ChatDto
import com.example.vintedandroid.viewmodel.ChatViewModel

@Composable
fun ChatActivity(chatViewModel: ChatViewModel) {


    //    val userFromDB1: State<UserDatabaseDto?> = userViewModel.getAllUserFromRoomDatabase().collectAsState(initial = null)
    //var allChat = chatViewModel.getAllChat()

    val allChat: State<Array<ChatDto>?> = chatViewModel.getAllChat()!!.collectAsState(initial = null)

    Box(){
        Row(){
            Column() {
                Text(text = "Contact")
                Card {
                    LazyColumn {
                        if(allChat.value != null) {
                            items(allChat.value!!) { chatItem ->
                                ChatRow(chatItem)
                            }
                        }
                    }
                }
            }
            Column() {
                Text(text = "Chat effettive")
            }

        }

    }

}

@Composable
fun ChatRow(chatItem :  ChatDto) {

    Card() {
        Text(text = chatItem.user1.toString())
        chatItem.user2?.let { Text(text = it) }

    }

}