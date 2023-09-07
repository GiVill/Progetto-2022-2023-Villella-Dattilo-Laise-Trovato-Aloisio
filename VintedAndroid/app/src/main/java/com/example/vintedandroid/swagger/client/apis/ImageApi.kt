/**
 * OpenAPi Vinted
 * OpenApi documentation for Spring Security
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package com.example.vintedandroid.swagger.client.apis

import android.graphics.Bitmap
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.swagger.client.infrastructure.*
import com.example.vintedandroid.swagger.client.models.ImagesUserBody
import com.example.vintedandroid.swagger.client.models.InsertionInsertionIdBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.ByteArrayOutputStream
import java.io.IOException


class ImageApi(basePath: kotlin.String = "https://192.168.1.90:8010/vintedProject-api") : ApiClient(basePath) {

    /**
     *
     *
     * @param userId
     * @return void
     */
    fun adminDeleteImage(userId: kotlin.Long): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/admin/images/user/{userId}".replace("{" + "userId" + "}", "$userId")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @param insertionId
     * @return void
     */
    fun adminDeleteImageInsertion(insertionId: kotlin.Long): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/admin/images/insertion/{insertionId}".replace("{" + "insertionId" + "}", "$insertionId")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @param imagePath
     * @return kotlin.Array<kotlin.Byte>
     */
    @Suppress("UNCHECKED_CAST")
    fun getImageById(imagePath: kotlin.String): kotlin.Array<kotlin.Byte> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/images/{imagePath}".replace("{" + "imagePath" + "}", "$imagePath")
        )
        val response = request<kotlin.Array<kotlin.Byte>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<kotlin.Byte>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @param body
     * @param insertionId
     * @return kotlin.Boolean
     */
    @Suppress("UNCHECKED_CAST")
    fun insertInsertionImage(bitmap: Bitmap, insertionId: kotlin.Long): kotlin.Boolean {

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val byteArray = stream.toByteArray()

        val url = "https://192.168.1.90:8010/vintedProject-api/v1/images/insertion/{insertionId}".replace("{" + "insertionId" + "}", "$insertionId")

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("img", "image.jpg", RequestBody.create("image/jpeg".toMediaType(), byteArray))
            .build()

        val request = Request.Builder()
            .url(url) // Replace with your backend URL
            .addHeader("Authorization", "Bearer ${LoggedUserDetails.getInstance().getCurrentUser().accessToken}")
            .post(requestBody)
            .build()

        try {
            val response: Response = OkHttpClient().newCall(request).execute()
            return response.isSuccessful
        } catch (e: IOException) {
            return false
        }
    }
    /**
     * 
     * 
     * @param body  
     * @return kotlin.Boolean
     */
    @Suppress("UNCHECKED_CAST")
    fun insertUserImage(body: ImagesUserBody): kotlin.Boolean {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/images/user"
        )
        val response = request<kotlin.Boolean>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Boolean
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param userId  
     * @return void
     */
    fun userDeleteImage(userId: kotlin.Long): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/images/user/{userId}".replace("{" + "userId" + "}", "$userId")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param insertionId  
     * @return void
     */
    fun userImageInsertion(insertionId: kotlin.Long): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/images/insertion/{insertionId}".replace("{" + "insertionId" + "}", "$insertionId")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
