package com.example.vintedandroid.view

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tab
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.example.vintedandroid.client.apis.ImageApi
import com.example.vintedandroid.client.models.UserUserIdBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream
import java.io.IOException


@Composable
fun AddScreen() {
    AppContent()
}

@OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
@Composable
fun AppContent() {

    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    //val byteArray = remember { mutableStateOf(emptyArray<Byte>()) }
    //val byteArray = Array<Byte>();

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Insert Title")
        TextField(
            value = title.value,
            onValueChange = { title.value = it; },
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth(1f),
            singleLine = true,
            placeholder = { Icon(Icons.Default.Tab, contentDescription = "Title") }
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = "Insert Description")
        TextField(
            value = description.value,
            onValueChange = { description.value = it; },
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth(1f),
            singleLine = true,
            placeholder = { Icon(Icons.Default.Tab, contentDescription = "Title") }
        )

        Button(
            onClick = { galleryLauncher.launch("image/*") },
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
        ) {
            Text(text = "Pick Image From Gallery")
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(selectImages) { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .size(100.dp)
                        .clickable { /**/}
                )
                Button(
                    onClick = {

                        Log.i("tag", "Questa è url => $uri, ${uri.path}")

                        val file = File(uri.path)

                        //.value = try {
                           // byteArray = fileToByteArray(file)
                        //} catch (e: IOException) {
                            // Handle any potential exceptions, such as file not found or permission issues
                        //    emptyArray()
                        //}

                        //val c: UserUserIdBody = byteArray

                        //val response = ImageApi().insertUserImage(by)

                        //val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                        //val body = MultipartBody.Part.createFormData("image", file.name, requestFile)
/*
                        // Make the API call
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val response = ImageApi().insertUserImage(body,1L)
                                // Handle the response
                                // ...
                                Log.i("tag", response.toString())
                                Log.i("tag", "OKKKKKKKKKKKKK, immagine inviata")
                            } catch (e: Exception) {
                                // Handle the error
                                // ...
                                Log.i("tag", e.toString())
                                Log.i("tag", "NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
                            }
                        }
                        */

                        /*
                        val formData = formData {
                            append("image", imageFile)
                        }

                        val response: BackendResponse = httpClient.post("$backendUrl/upload") {
                        body = MultiPartFormDataContent(formData)
                        headers.append(Headers.ContentType, "multipart/form-data")
                        }

                         */
                    },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(10.dp)
                ) {
                    Text(text = "Send Image!")
                }
            }
        }

        Button(onClick = {
            /*TODO*/
        }) {
            Text(text = "Crea inserzione")
        }

    }
}
/*
fun <T> Array(): Array<T> {

}

fun fileToByteArray(file: File): Array<Byte> {
    val inputStream = FileInputStream(file)
    val byteArray = inputStream.readBytes()
    inputStream.close()

    return byteArray.toTypedArray()
}

 */

/*
private operator fun <T> State<T>.setValue(t: T?, property: KProperty<T?>, any: Any) {

}

private operator fun <T> MutableState<T>.setValue(t: T?, property: KProperty<T?>, any: Any) {

}

private operator fun Any.getValue(nothing: Nothing?, property: KProperty<*>): Any {

}

 */

@Preview
@Composable
fun AddScreenPreview() {
    AddScreen()
}