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
import com.example.vintedandroid.client.models.OrderDto
import com.example.vintedandroid.client.models.PageOrderDto

import com.example.vintedandroid.client.infrastructure.*
import com.example.vintedandroid.client.infrastructure.MultiValueMap

class OrderApi(basePath: kotlin.String = "https://localhost:8010/vintedProject-api") : ApiClient(basePath) {

    /**
     * 
     * 
     * @param body  
     * @return OrderDto
     */
    @Suppress("UNCHECKED_CAST")
    fun addOrder(body: OrderDto): OrderDto {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v1/orders"
        )
        val response = request<OrderDto>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as OrderDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param page  
     * @return PageOrderDto
     */
    @Suppress("UNCHECKED_CAST")
    fun all2(page: kotlin.Int): PageOrderDto {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/orders", query = localVariableQuery
        )
        val response = request<PageOrderDto>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageOrderDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return void
     */
    fun deleteOrderById(id: kotlin.Long): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/v1/orders/{id}".replace("{" + "id" + "}", "$id")
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
     * @param id  
     * @return OrderDto
     */
    @Suppress("UNCHECKED_CAST")
    fun getOrderById(id: kotlin.Long): OrderDto {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v1/orders/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<OrderDto>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as OrderDto
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
