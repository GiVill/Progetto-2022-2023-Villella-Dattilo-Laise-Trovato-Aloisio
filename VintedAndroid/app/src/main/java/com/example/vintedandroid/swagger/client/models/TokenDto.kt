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
 * @param accessToken 
 * @param refreshToken 
 * @param tokenType 
 * @param userDto 
 */
data class TokenDto (

    val accessToken: kotlin.String? = null,
    val refreshToken: kotlin.String? = null,
    val tokenType: kotlin.String? = null,
    val userDto: UserDto? = null
) {
}