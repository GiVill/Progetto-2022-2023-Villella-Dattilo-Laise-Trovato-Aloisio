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


    fun updateImage(bitmap: Bitmap) {
        this.bitmap = bitmap
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
                brand = null,
                category = null,
                available= true,
                userId = it
            )
        }
    }

}