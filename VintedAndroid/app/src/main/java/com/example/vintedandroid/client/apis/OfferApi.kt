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
import com.example.vintedandroid.client.models.BuyingOfferDto
import com.example.vintedandroid.client.models.InlineResponse200

import com.example.vintedandroid.client.infrastructure.*

class OfferApi(basePath: kotlin.String = "https://localhost:8010/vintedProject-api") : ApiClient(basePath) {

    /**
     * 
     * 
     * @param body  
     * @return BuyingOfferDto
     */
    @Suppress("UNCHECKED_CAST")
    fun addBuyingOffer(body: BuyingOfferDto): BuyingOfferDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/offers"
        )
        val response = request<BuyingOfferDto>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BuyingOfferDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @return kotlin.Array<BuyingOfferDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun all3(): kotlin.Array<BuyingOfferDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/offers"
        )
        val response = request<kotlin.Array<BuyingOfferDto>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<BuyingOfferDto>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param idUser  
     * @return InlineResponse200
     */
    @Suppress("UNCHECKED_CAST")
    fun allId(idUser: kotlin.Long): InlineResponse200 {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/offers/{idUser}".replace("{" + "idUser" + "}", "$idUser")
        )
        val response = request<InlineResponse200>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse200
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param idOffer  
     * @return kotlin.String
     */
    @Suppress("UNCHECKED_CAST")
    fun delete(idOffer: kotlin.Long): kotlin.String {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/offers/{idOffer}".replace("{" + "idOffer" + "}", "$idOffer")
        )
        val response = request<kotlin.String>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.String
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}