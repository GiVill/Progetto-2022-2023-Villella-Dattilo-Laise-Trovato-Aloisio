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

import android.util.Log
import com.example.vintedandroid.model.LoggedUserDetails
import com.example.vintedandroid.swagger.client.infrastructure.ApiClient
import com.example.vintedandroid.swagger.client.infrastructure.ClientError
import com.example.vintedandroid.swagger.client.infrastructure.ClientException
import com.example.vintedandroid.swagger.client.infrastructure.RequestConfig
import com.example.vintedandroid.swagger.client.infrastructure.RequestMethod
import com.example.vintedandroid.swagger.client.infrastructure.ResponseType
import com.example.vintedandroid.swagger.client.infrastructure.ServerError
import com.example.vintedandroid.swagger.client.infrastructure.ServerException
import com.example.vintedandroid.swagger.client.infrastructure.Success
import com.example.vintedandroid.swagger.client.models.BuyingOfferDto

import com.example.vintedandroid.swagger.client.infrastructure.*

class OfferApi(basePath: kotlin.String = "https://192.168.1.90:8010/vintedProject-api") : ApiClient(basePath) {

    /**
     * 
     * 
     * @param body  
     * @return kotlin.String
     */
    @Suppress("UNCHECKED_CAST")
    fun acceptOffers(body: BuyingOfferDto): kotlin.String {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/v1/offers/accept"
        )
        ConfigureAuthorizationBearer(localVariableConfig)
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
     * @return BuyingOfferDto
     */
    @Suppress("UNCHECKED_CAST")
    fun adminAddBuyingOffer(body: BuyingOfferDto): BuyingOfferDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/admin/offers"
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
     * @param idOffer  
     * @return kotlin.String
     */
    @Suppress("UNCHECKED_CAST")
    fun adminDeleteOffer(idOffer: kotlin.Long): kotlin.String {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/admin/offers/{idOffer}".replace("{" + "idOffer" + "}", "$idOffer")
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
    /**
     * 
     * 
     * @param insertionId  
     * @return kotlin.Array<BuyingOfferDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun adminGetAllByInsertionId(insertionId: kotlin.Long): kotlin.Array<BuyingOfferDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/admin/offers/insertion/{insertionId}".replace("{" + "insertionId" + "}", "$insertionId")
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
     * @return kotlin.Array<BuyingOfferDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getAll2(): kotlin.Array<BuyingOfferDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/admin/offers"
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
     * @return kotlin.Array<BuyingOfferDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getAllByUser(): kotlin.Array<BuyingOfferDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/offers/user"
        )
        ConfigureAuthorizationBearer(localVariableConfig)

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
     * @return kotlin.Array<BuyingOfferDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun getAllByUserId(idUser: kotlin.Long): kotlin.Array<BuyingOfferDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/admin/offers/user/{idUser}".replace("{" + "idUser" + "}", "$idUser")
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
     * @param offerId  
     * @return BuyingOfferDto
     */
    @Suppress("UNCHECKED_CAST")
    fun getById(offerId: kotlin.Long): BuyingOfferDto {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/admin/offers/{offerId}".replace("{" + "offerId" + "}", "$offerId")
        )
        val response = request<BuyingOfferDto>(
                localVariableConfig
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
     * @param body  
     * @return BuyingOfferDto
     */
    @Suppress("UNCHECKED_CAST")
    fun userAddBuyingOffer(body: BuyingOfferDto): BuyingOfferDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/offers"
        )

        ConfigureAuthorizationBearer(localVariableConfig)

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
    /*
    @Suppress("UNCHECKED_CAST")
    fun userAddBuyingOffer(body: BuyingOfferDto): BuyingOfferDto {
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
     */

    /**
     * 
     * 
     * @param body  
     * @return kotlin.String
     */
    @Suppress("UNCHECKED_CAST")
    fun userDeleteOffer(body: BuyingOfferDto): kotlin.String {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/offers"
        )
        ConfigureAuthorizationBearer(localVariableConfig)
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
     * @param insertionId  
     * @return kotlin.Array<BuyingOfferDto>
     */
    @Suppress("UNCHECKED_CAST")
    fun userGetAllByInsertionId(insertionId: kotlin.Long): kotlin.Array<BuyingOfferDto> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/offers/insertion/{insertionId}".replace("{" + "insertionId" + "}", "$insertionId")
        )
        ConfigureAuthorizationBearer(localVariableConfig)
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
}
