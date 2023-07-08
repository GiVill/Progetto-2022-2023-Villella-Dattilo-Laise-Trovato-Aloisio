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
 * @param totalElements 
 * @param totalPages 
 * @param size 
 * @param content 
 * @param number 
 * @param sort 
 * @param first 
 * @param last 
 * @param numberOfElements 
 * @param pageable 
 * @param empty 
 */
data class PageBasicInsertionDto (
    val totalElements: Long? = null,
    val totalPages: Int? = null,
    val size: Int? = null,
    val content: Array<BasicInsertionDto>? = null,
    val number: Int? = null,
    val sort: SortObject? = null,
    val first: Boolean? = null,
    val last: Boolean? = null,
    val numberOfElements: Int? = null,
    val pageable: PageableObject? = null,
    val empty: Boolean? = null
) {
    val results: List<BasicInsertionDto>
        get() = content.orEmpty().toList()
}
