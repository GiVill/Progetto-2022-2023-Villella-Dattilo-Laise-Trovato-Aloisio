package com.example.vintedandroid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vintedandroid.theme.VintedAndroidTheme
import com.example.vintedandroid.view.LoginScreen
import com.example.vintedandroid.view.ScreenController
import com.example.vintedandroid.view.SearchResultCard
import com.example.vintedandroid.view.SetupNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.nio.ByteBuffer
import android.Manifest
class MainActivity : ComponentActivity() {

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
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VintedAndroidTheme {
                StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitNetwork().build())

                if (checkForInternet(this)) {
                    //Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
                    //Toast.makeText(this,"a", Toast.LENGTH_SHORT).show()

                    /*
                    //QUESTE FUNZIONANO SOLO SE IL TUO IP è 192.168.1.90! AGLI ALTRI DARà ERRORE!!
                    val userApi = UserApi()
                    val users = userApi.all()
                    users.forEach { user ->
                        Log.i("papà",user.toString())
                    }
                     */

                    //var c = AppDatabase.getInstance(context = application.applicationContext).userDao().getAll()



                    val navController = rememberNavController()

                    val searchText = remember { mutableStateOf("") }

                    var controlloSuToken = true //questa variabile servirebbe per capire se all'avvio dell'app hai fatto il login oppure no. Per ora non si fa nessun controllo

                    if(controlloSuToken) {

                        Scaffold(
                            topBar = { ApplicationTopBar(searchText, navController) },
                            bottomBar = { ApplicationBottomBar(navController) },
                            content = {
                            Box(modifier = Modifier.padding(it)) {


                                //val imageView = ImageView(application.applicationContext)  // Replace `context` with your actual context object
                                val imagePath = "content://com.android.providers.downloads.documents/document/msf%3A1000009051"
                                //displayImageInImageView(application.applicationContext, imagePath, imageView)

                                CoroutineScope(Dispatchers.IO).launch {

                                    // Inside your activity or fragment
                                    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
                                    } else {
                                        // Permission already granted, proceed with opening the document
                                        openDocument()
                                    }

                                    //openDocument()

                                    //val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                                    //ActivityCompat.requestPermissions(this, permissions, requestCode)
/*
                                    val byteObjects: Array<Byte?> =
                                        imageToByteArray(application, imagePath)
                                    Log.i("tag", "OK => ${byteObjects.toString()}")

 */
                                    //pickImage()
                                }
                                CoroutineScope(Dispatchers.IO).launch {
                                    //val user1 = UserDatabaseDto(UUID.randomUUID().toString(), "ciao", "Boh", "ciaoBoh")
                                    //AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().insert(user1) //Inserimento di un utente
                                    //var c = AppDatabase.getInstance(context = application.applicationContext).userDatabaseDao().getAll() //Get di un utente
                                    //Log.i("tag", c.toString())
                                }
                                SetupNavGraph(
                                    navController = navController,
                                    searchText = searchText,
                                    application = application.applicationContext
                                )
                            }
                        } )
                    }
                    else{
                        LoginScreen(navController = navController, application) //Non funziona bene
                    }
                } else {
                    Scaffold(){
                        Box(modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                            contentAlignment = Alignment.Center ) {

                            Card(modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()) {
                                Text(text = "Please connect to the internet", fontSize = 40.sp)
                                Spacer(modifier = Modifier.width(3.dp))
                                /*
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "No Internet Connection",
                                    //tint = Color.Red
                                )
                                 */
                                
                            }
                        }
                    }
                    Toast.makeText(this, "You are not connected to the Internet!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }





}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationTopBar(searchText: MutableState<String>, navController: NavHostController) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val topBarHandler by remember(currentBackStackEntry) { derivedStateOf{navController.currentBackStackEntry?.destination?.route == ScreenController.Search.route} }

    //var text by remember { mutableStateOf("") }
    /*
    var active by remember { mutableStateOf(false) }
    var searchHistory = remember{ mutableStateListOf("ciao", "ciao2") }

    var searchResults by remember { mutableStateOf(PageBasicInsertionDto()) }
    val coroutineScope = rememberCoroutineScope()
    val insertionApi = InsertionApi()
    var searchedProduct = remember {
        mutableStateOf(
            BasicInsertionDto(1L,"null", Float.MIN_VALUE,null,null,null,null,null,"",
                BasicInsertionDto.Brand.ADIDAS,
                BasicInsertionDto.Category.ABBIGLIAMENTO, 2L)
        )

    }

     */


    TopAppBar(
        title = {
            if(!topBarHandler){
                Text("Vinted",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        actions = {
            if(topBarHandler) {
                //navController.popBackStack(); navController.navigate("search")



            TextField(
                value = searchText.value,
                onValueChange = { searchText.value = it; },
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth(1f),
                singleLine = true,
                placeholder = { Icon(Icons.Default.Search, contentDescription = "Search")}
            )

        }

            //}
        }
    )

    /*
    TopBar fatta da Umberto

    TopAppBar(
        title = { Text(text = "VinteDroid") },
        navigationIcon = {
            IconButton(onClick = { true }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /* Open search screen */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
    )

    */

    /*

    TopBar del professore

    //val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    //val showBackIcon by remember(currentBackStackEntry) { derivedStateOf { navHostController.previousBackStackEntry != null } }


    TopAppBar(title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            }
        },
        actions = {
            androidx.compose.material3.IconButton(onClick = {}) {
                androidx.compose.material3.Icon(
                    Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.app_name)
                )
            }
        }
    )

     */
        
}


@Composable
fun ApplicationBottomBar(navController: NavHostController) {//,selectedIndex: MutableState<Int>

    // TUTTI i navController.popBackStack(); ANDREBBERO gestiti diversamente, per ora sono ni

    BottomAppBar {

        BottomNavigation {
            BottomNavigationItem(
                selected = true,
                onClick = { navController.popBackStack(); navController.navigate("home") },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text(text = "Navigate") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.popBackStack(); navController.navigate("search") }, //navController.navigate("search")
                icon = { Icon(Icons.Default.Search, contentDescription = "search") },
                label = { Text(text = "Search") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.popBackStack(); navController.navigate("add") }, //navController.navigate("add")
                icon = { Icon(Icons.Default.AddCircle, contentDescription = "add_circle") },
                label = { Text(text = "Add") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.popBackStack(); navController.navigate("bottom-bar-profile") }, //navController.navigate("profile")
                icon = { Icon(Icons.Default.AccountCircle, contentDescription = "add_circle") },
                label = { Text(text = "Profile") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.popBackStack(); navController.navigate("cart") },
                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                label = { Text(text = "Cart") }
            )
        }
        /*
        NavigationBar {
            NavigationBarItem(
                selected = selectedIndex.value == 0,
                onClick = { selectedIndex.value = 0 },
                icon = {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            )
            NavigationBarItem(
                selected = selectedIndex.value == 1,
                onClick = { selectedIndex.value = 1 },
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            )
        }
    }

         */
    }
}


private fun checkForInternet(context: Context): Boolean {

    // register activity with the connectivity manager service
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // if the android version is equal to M
    // or greater we need to use the
    // NetworkCapabilities to check what type of
    // network has the internet connection
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    } else {
        // if the android version is below M
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }
}

data class Item(val name: String, val description: String, val price: Float)
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

/*
fun displayImageInImageView(context: Context, imagePath: String, imageView: ImageView) {
    Glide.with(context).load(imagePath).into(imageView)
    /*
    val contentResolver = context.contentResolver
    val imageUri = Uri.parse(imagePath)

    try {
        val inputStream = contentResolver.openInputStream(imageUri)
        val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)

        // Set the bitmap in the ImageView
        imageView.setImageBitmap(bitmap)

        inputStream?.close()
        Log.i("tag", "UOOOU")
    } catch (e: FileNotFoundException) {
        Log.i("tag", "NOIN")

        println("Image file not found.")
    } catch (e: Exception) {
        Log.i("tag", "NEIN")

        println("Error retrieving the image: ${e.message}")
    }

     */
}

 */

fun imageToByteArray(context: Context, imagePath: String): Array<Byte?> {
    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
    val bitmap: Bitmap = Glide.with(context)
        .asBitmap()
        .apply(requestOptions)
        .load(imagePath)
        .submit()
        .get()

    return bitmap.toByteArray()
}

// Extension function to convert a Bitmap to a byte array
fun Bitmap.toByteArray(): Array<Byte?> {
    val byteBuffer = ByteBuffer.allocate(byteCount)
    copyPixelsToBuffer(byteBuffer)
    return byteBuffer.array().map { it }.toTypedArray()
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VintedAndroidTheme {
        //MainScreen()
    }
}
