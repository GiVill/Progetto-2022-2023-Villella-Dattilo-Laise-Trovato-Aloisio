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
package com.example.vintedandroid.client.models


/**
 * 
 * @param access_token
 * @param refresh_token
 * @param token_type
 * @param userDto
 */
data class TokenResponse (

    val access_token: kotlin.String? = null,
    val refresh_token: kotlin.String? = null,
    val token_type: kotlin.String? = null,
    val userDto: UserDto? = null
) {
}