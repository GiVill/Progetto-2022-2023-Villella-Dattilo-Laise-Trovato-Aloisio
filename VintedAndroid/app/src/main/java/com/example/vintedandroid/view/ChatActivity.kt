package com.example.vintedandroid.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vintedandroid.R
import com.example.vintedandroid.swagger.client.models.ChatDto
import com.example.vintedandroid.theme.Typography
import com.example.vintedandroid.viewmodel.ChatViewModel



@Composable
fun ChatActivity( chatViewModel: ChatViewModel,  navController: NavHostController) {


    //    val userFromDB1: State<UserDatabaseDto?> = userViewModel.getAllUserFromRoomDatabase().collectAsState(initial = null)
    //var allChat = chatViewModel.getAllChat()

    //val allChat: State<Array<ChatDto>?> = chatViewModel.getAllChat()!!.collectAsState(initial = null)
    val chatFromDB: Array<ChatDto>? = try {
        chatViewModel.getAllChat()
    } catch (e: Exception) {
        null
    }
    Box() {
        Row() {
            Column(modifier =
            Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())) {
                chatFromDB?.forEach { chat ->
                    if (!chat.insertionTitle.isNullOrEmpty()) {
                        ClickableText(
                            text = AnnotatedString(chat.insertionTitle) + AnnotatedString(" | "  ) + AnnotatedString(chat.user2NameLastname!!),
                            modifier = Modifier.padding(20.dp),
                            onClick = { offset ->
                                navController.navigate("${ScreenController.ChatMessage.route}/${chat.id}")
                            },
                            style = TextStyle(color = Color.White, fontSize = Typography.titleMedium.fontSize)
                        )
                    }

                    Divider()
                    Spacer(modifier = Modifier.height(15.dp))
                }
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

