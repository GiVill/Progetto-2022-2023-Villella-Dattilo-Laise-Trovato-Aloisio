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
QUESTA ERA UNA SEZIONE FATTA PER UN TEST CHE è RIUSCITO SOLO IN PARTE (Ad aprire la galleria del telefono e selezionare una foto, e basta. Potrei aver copiate una sezione di troppo)


private val OPEN_DOCUMENT_REQUEST_CODE = 1
    private val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1

    fun openDocument() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, OPEN_DOCUMENT_REQUEST_CODE)
    }

    // Handle the permission request result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with opening the document
                openDocument()
            } else {
                // Permission denied, handle accordingly (e.g., show an error message)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OPEN_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                // Perform operations on the document using its URI.
            }
        }
    }

 */

//////////////////////////////////////////////////////////////////////////////////////////////////////////
                        Box(modifier = Modifier.padding(it)) {
                                //val imageView = ImageView(application.applicationContext)  // Replace `context` with your actual context object
                                //val imagePath = "content://com.android.providers.downloads.documents/document/msf%3A1000009051"
                                //displayImageInImageView(application.applicationContext, imagePath, imageView)

                                CoroutineScope(Dispatchers.IO).launch {
                                    /*
                                    //TODO Funziona, ma non va aperta qui la galleria  delle immagini
                                    // Inside your activity or fragment
                                    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
                                    } else {
                                        // Permission already granted, proceed with opening the document
                                        openDocument()
                                    }
                                    */



                                    //val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                                    //ActivityCompat.requestPermissions(this, permissions, requestCode)
/*
                                    val byteObjects: Array<Byte?> =
                                        imageToByteArray(application, imagePath)
                                    Log.i("tag", "OK => ${byteObjects.toString()}")

 */
                                    //pickImage()
                                }
                                }

//////////////////////////////////////////////////////////////////////////////////////////////////////////


/*
@Composable
fun pickImage() {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        uri:Uri? -> imageUri = uri
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        imageUri?.let{
            if(Build.VERSION.SDK_INT < 28){
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }else{
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value =ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(20.dp)
                )
            }
            Log.i("tag", launcher.toString())
            Log.i("tag", imageUri.toString())
        }

        Log.i("tag", imageUri.toString())

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {launcher.launch("image/*");  Log.i("tag", launcher.toString())}) {
            Text(text = "Pick Image")

        }

    }

}*/*/

*/

@Preview
@Composable
fun AddScreenPreview() {
    //AddScreen()
}