package com.example.vintedandroid.view

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Title
import androidx.compose.ui.text.input.TextFieldValue

//import com.example.vintedandroid.swagger.client.models.UserUserIdBody
import com.example.vintedandroid.view.config.createPersonalizedTextfield
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.vintedandroid.R
import com.example.vintedandroid.view.config.createCheckbox
import com.example.vintedandroid.viewmodel.AddViewModel

@Composable
fun AddActivity(addViewModel: AddViewModel) {

    val titleField = remember { mutableStateOf(TextFieldValue()) }
    val descriptionField = remember { mutableStateOf(TextFieldValue()) }
    val priceField = remember { mutableStateOf(TextFieldValue()) }
    val checkStatus = remember { mutableStateOf(false) }

    val nameRegex = "^[A-Za-z\\s]{2,}\$".toRegex()
    val numberRegex = "^\\d+\$".toRegex()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        if (it != null) {
            addViewModel.updateImage(it)
        }
    } // Take a picture

    Box(Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            createPersonalizedTextfield(textField = titleField, name = stringResource(R.string.title_of_insertion), icon = Icons.Default.Title, regexPattern = nameRegex)
            createPersonalizedTextfield(textField = descriptionField, name = stringResource(R.string.description_of_insertion), icon = Icons.Default.Description, regexPattern = nameRegex)
            createPersonalizedTextfield(textField = priceField, name = stringResource(R.string.price_of_insertion), icon = Icons.Default.MonetizationOn, regexPattern = numberRegex)
            createCheckbox(checkStatus = checkStatus)

            Card(modifier = Modifier.padding(6.dp)) {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text("Add An Image")
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(R.string.add_image),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .clickable { launcher.launch() } //launch the code to take a picture
                    )
                }
            }
            Button(onClick = {
                addViewModel.addInsertion(titleField.value.text, descriptionField.value.text, priceField.value.text.toFloat(), checkStatus.value)
            }) {
                Text("Send New Insertion")
            }
        }
    }
}