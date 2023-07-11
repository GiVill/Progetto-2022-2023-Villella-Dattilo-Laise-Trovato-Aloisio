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
package com.example.vintedandroid.client.apis

import com.example.vintedandroid.client.infrastructure.ApiClient
import com.example.vintedandroid.client.infrastructure.ClientError
import com.example.vintedandroid.client.infrastructure.ClientException
import com.example.vintedandroid.client.infrastructure.RequestConfig
import com.example.vintedandroid.client.infrastructure.RequestMethod
import com.example.vintedandroid.client.infrastructure.ResponseType
import com.example.vintedandroid.client.infrastructure.ServerError
import com.example.vintedandroid.client.infrastructure.ServerException
import com.example.vintedandroid.client.infrastructure.Success
import com.example.vintedandroid.client.models.LoginUserDto
import com.example.vintedandroid.client.models.NewUserDto
import com.example.vintedandroid.client.models.TokenResponse

import com.example.vintedandroid.client.infrastructure.*

class AuthApi(basePath: kotlin.String = "https://192.168.1.90:8010/vintedProject-api") : ApiClient(basePath) {

    /**
     * 
     * 
     * @param body  
     * @return kotlin.String
     */
    @Suppress("UNCHECKED_CAST")
    fun getRefreshToken(body: kotlin.String): kotlin.String {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/get-refresh-token"
        )
        val response = request<kotlin.String>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.String
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
     * @return TokenResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun login(body: LoginUserDto): TokenResponse {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/login"
        )
        val response = request<TokenResponse>(
                localVariableConfig, localVariableBody
        )

        val t = TokenResponse(
            access_token = null
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TokenResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> t//throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @return TokenResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun signUp(body: NewUserDto): TokenResponse {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/sign-up"
        )
        val response = request<TokenResponse>(
                localVariableConfig, localVariableBody
        )

        val t = TokenResponse(
            access_token = null
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as TokenResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> t//throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
