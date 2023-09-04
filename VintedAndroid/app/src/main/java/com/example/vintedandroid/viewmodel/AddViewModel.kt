package com.example.vintedandroid.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.ImageApi
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.models.InsertionInsertionIdBody
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.sql.Blob
import kotlin.math.absoluteValue

class AddViewModel (application: Application) : ViewModel() {

    fun updateImage(bitmap: Bitmap, insertionId: Long) {
        val a = InsertionInsertionIdBody(
            img= bitmapToByteArray(bitmap)
        )
        ImageApi().insertInsertionImage(a, insertionId)

    }



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

    /*
    fun updateBitmap(bitmap: Bitmap?) {
        val byteBuffer = bitmap?.let { ByteBuffer.allocate(it.byteCount) }
        if (byteBuffer != null) {
            Log.i("bitmap", "${bitmap?.copyPixelsToBuffer(byteBuffer)},\n ${byteBuffer.array().contentToString()}, \n ${byteBuffer.array().get(byteBuffer.array().size-1)}")
        }
    }

     */

}