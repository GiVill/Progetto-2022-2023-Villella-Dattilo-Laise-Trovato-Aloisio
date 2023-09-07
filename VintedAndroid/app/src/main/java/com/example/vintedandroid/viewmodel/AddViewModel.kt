package com.example.vintedandroid.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.swagger.client.apis.ImageApi
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.InsertionInsertionIdBody
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.sql.Blob
import kotlin.math.absoluteValue

class AddViewModel (application: Application) : ViewModel() {

    var bitmap: Bitmap? = null
    private val application = application


    fun updateImage(bitmap: Bitmap, insertionId: Long) {
        this.bitmap = bitmap
        //ImageApi().insertInsertionImage(bitmap, insertionId)
    }

    fun addInsertion(title: String, description: String, price: Float, isPrivate: Boolean){
        val insertionDto = convertToInsertionDto(title, description, price, isPrivate)
        Log.i("AddInsertion", "$insertionDto , $bitmap")
        bitmap?.let {
            if (insertionDto != null) {
                if(LoggedUserDetails.getInstance().getCurrentUser().id != null){
                    if(InsertionApi().addInsertion(it, insertionDto)){
                        Toast.makeText(application.applicationContext, "Insertion Sended", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(application.applicationContext, "There was an error with the server", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(application.applicationContext, "You need to login first!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(application.applicationContext, "You need to login first!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun convertToInsertionDto(title: String, description: String, price: Float, isPrivate: Boolean): BasicInsertionDto?{

        return LoggedUserDetails.getInstance().getCurrentUser().id?.let {
            BasicInsertionDto(
                id = null,
                title = title,
                price = price,
                description = description,
                creationDate = null,
                isPrivate = isPrivate,
                imageName = null,
                brand = null,//BasicInsertionDto.Brand.ADIDAS,
                category = null, //
                available= true,
                userId = it
            )
        }
        /*
            val id: Long? = null,
    val title: String,
    val price: Float,
    val description: String,
    val creationDate: Any? = null,
    val isPrivate: Boolean? = null,
    val imageName: String? = null,
    val brand: BasicInsertionDto.Brand? = null,
    val category: BasicInsertionDto.Category? = null,
    val available: Boolean? = null,
    val userId: Long
             */
    }



/*
    // Function to convert Bitmap to byte array
    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        Log.i("get", "${outputStream.toByteArray().contentToString()}")
        return outputStream.toByteArray()
    }

    fun getByteArrayFromBitmap(bitmap: Bitmap): ByteArray {
        val byteBuffer = ByteBuffer.allocate(bitmap.byteCount)
        bitmap.copyPixelsToBuffer(byteBuffer)
        Log.i("getByteArrayFromBitmap", "${byteBuffer.array().contentToString()}")
        return byteBuffer.array()
    }

 */

    /*
    fun updateBitmap(bitmap: Bitmap?) {
        val byteBuffer = bitmap?.let { ByteBuffer.allocate(it.byteCount) }
        if (byteBuffer != null) {
            Log.i("bitmap", "${bitmap?.copyPixelsToBuffer(byteBuffer)},\n ${byteBuffer.array().contentToString()}, \n ${byteBuffer.array().get(byteBuffer.array().size-1)}")
        }
    }

     */

}