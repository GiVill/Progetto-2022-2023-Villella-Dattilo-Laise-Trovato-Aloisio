package com.example.vintedandroid.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
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

    fun updateImage(bitmap: Bitmap, insertionId: Long) {
        this.bitmap = bitmap
        //ImageApi().insertInsertionImage(bitmap, insertionId)
    }

    fun addInsertion(title: String, description: String, price: Float, isPrivate: Boolean){
        val insertionDto = convertToInsertionDto(title, description, price, isPrivate)
        Log.i("AddInsertion", "$insertionDto , $bitmap")
        bitmap?.let { InsertionApi().addInsertion(it, insertionDto) }
    }

    fun convertToInsertionDto(title: String, description: String, price: Float, isPrivate: Boolean): BasicInsertionDto{

        return BasicInsertionDto(
            title = title,
            price = price,
            description = description,
            isPrivate = isPrivate,
            userId = 0
        )
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