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
 * @param firstName 
 * @param lastName 
 * @param address 
 * @param nickname 
 * @param email 
 * @param password 
 * @param role 
 * @param phoneNumber 
 * @param registrationDate 
 * @param imageName 
 * @param isPrivate 
 * @param buyingOffers 
 * @param orders 
 * @param insertions 
 */
data class User (

    val id: kotlin.Long? = null,
    val firstName: kotlin.String? = null,
    val lastName: kotlin.String? = null,
    val address: Address? = null,
    val nickname: kotlin.String? = null,
    val email: kotlin.String? = null,
    val password: kotlin.String? = null,
    val role: Role? = null,
    val phoneNumber: kotlin.Int? = null,
    val registrationDate: java.time.LocalDate? = null,
    val imageName: kotlin.String? = null,
    val isPrivate: kotlin.Boolean? = null,
    val buyingOffers: kotlin.Array<BuyingOffer>? = null,
    val orders: kotlin.Array<Order>? = null,
    val insertions: kotlin.Array<BasicInsertion>? = null
) {
    /**
    * 
    * Values: ADMIN,USER
    */
    enum class Role(val value: kotlin.String){
        ADMIN("ADMIN"),
        USER("USER");
    }
}