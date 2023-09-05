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
package com.example.vintedandroid.swagger.client.models

/**
 * 
 * @param id 
 * @param user1 
 * @param user2 
 * @param chatMessage 
 * @param basicInsertion 
 */
data class ChatDto (

    val id: kotlin.Long? = null,
    val user1: kotlin.Long? = null,
    val user2: kotlin.String? = null,
    val chatMessage: ChatMessage? = null,
    val basicInsertion: BasicInsertion? = null
) {
}