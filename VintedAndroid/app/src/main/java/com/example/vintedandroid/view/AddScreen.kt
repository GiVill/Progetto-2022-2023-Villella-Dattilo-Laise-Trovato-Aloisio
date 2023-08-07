package com.example.vintedandroid.view

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tab
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.checkSelfPermission
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.example.vintedandroid.client.apis.ImageApi
import com.example.vintedandroid.client.apis.InsertionApi
import com.example.vintedandroid.client.models.BasicInsertionDto
import com.example.vintedandroid.client.models.LoginUserDto
import com.example.vintedandroid.client.models.UserUserIdBody
import com.example.vintedandroid.model.AppDatabase
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.view.config.createPersonalizedTextfield
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files


@Composable
fun AddScreen(application: Context) {
    AppContent(application)
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AppContent(application: Context) {


    val titleField = remember { mutableStateOf(TextFieldValue()) }
    val descriptionField = remember { mutableStateOf(TextFieldValue()) }
    //val priceField = remember { mutableStateOf(TextFieldValue()) }

    val imageView = ImageView(application)

    val price = remember { mutableStateOf("") }

    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    //val byteArray = remember { mutableStateOf(emptyArray<Byte>()) }
    //val byteArray = Array<Byte>();

    val userFromDB = remember { mutableStateListOf<UserDatabaseDto>() }
    var isLoaded = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (userFromDB.isEmpty()) {
            val databaseItems = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll()
            }
            userFromDB.clear()
            if(databaseItems.isNotEmpty()){
                userFromDB.addAll(databaseItems)
            }
            isLoaded.value = true
        }
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { selectImages = it }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        createPersonalizedTextfield(textField = titleField, name = "Insert Title", icon = Icons.Default.Email)
        createPersonalizedTextfield(textField = descriptionField, name = "Insert Description", icon = Icons.Default.Email)
        //createPersonalizedTextfield(textField = priceField, name = "Insert Price", icon = Icons.Default.Email)

        /*
        TextField(value = text,
            onValueChange = { text = "it"},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

         */

        val pattern = remember { Regex("^\\d+\$") }
        Text(text="aaa")
        TextField(
            value = price.value,
            onValueChange = { if (it.isEmpty() || it.matches(pattern)) { price.value = it } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
/*
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

 */

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
                        .clickable { /**/ }
                )
                Button(
                    onClick = {

                        Log.i("tag", "Questa è url => $uri, ${uri.path}")

                        //"/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Images/IMG-20230712-WA0003.jpg"
                        val file = File(uri.path)//uri.path

                        Log.i("tag", "ok? ${uri.path}")

                        val contentResolver: ContentResolver = application.contentResolver
                        val imageUri: Uri = Uri.parse(uri.toString())

                        try {
                            val inputStream = contentResolver.openInputStream(imageUri)
                            val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)

                            // You can now work with the 'bitmap' object as needed
                            // For example, you can display it in an ImageView or manipulate it

                            imageView.setImageBitmap(bitmap)

                            // ...

                            inputStream?.close()

                            Log.i("tag", "ok? ${bitmap.toString()}")
                        } catch (e: FileNotFoundException) {
                            println("Image file not found.")
                        } catch (e: Exception) {
                            println("Error retrieving the image: ${e.message}")
                        }
/*
                        val mediaType = "image/jpeg".toMediaTypeOrNull() // Use the appropriate media type for your file

                        val requestBody = MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart(
                                "file",
                                file.name,
                                RequestBody.create(mediaType, file)
                            )
                            .build()

 */


                        Log.i("tag", "bene1, $uri, ${uri.path}")
                        if(file.exists()) {
                            var requestBody = file.readBytes().toTypedArray()

                            // var requestBody = Files.readAllBytes(file.toPath())


                            var b = UserUserIdBody(
                                img = requestBody
                            )

                            var a = ImageApi()

                            var c = a.insertUserImage(b)

                            Log.i("tag", c.toString())

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
                        }
                        else{
                            Log.i("tag", "NOOOOOOOOOOOOO")
                        }
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
            var c = InsertionApi()

            if(isLoaded.value) {

                var body = convertBasicInsertionDTO(
                    titleField.value.text,
                    descriptionField.value.text,
                    price.value.toFloat(),
                    userFromDB[0].id
                )

                c.addInsertion(body)

            }
        }) {
            Text(text = "Crea inserzione")
        }

    }
}

private fun convertBasicInsertionDTO(title: String, description: String, price: Float, id: Long?): BasicInsertionDto {
    return BasicInsertionDto(
        id = Long.MIN_VALUE,
        title= title,
        description= description,
        price= price,
        condition = "",
        creationDate= null,
        isPrivate= false,
        endDate=  null,
        imageName = "",
        brand= BasicInsertionDto.Brand.ADIDAS,
        category= BasicInsertionDto.Category.ABBIGLIAMENTO,
        userId= id
    )
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

/*
@Composable
fun pickImage() {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context: LocalCont
    
}

 */


@Preview
@Composable
fun AddScreenPreview() {
    //AddScreen()
}