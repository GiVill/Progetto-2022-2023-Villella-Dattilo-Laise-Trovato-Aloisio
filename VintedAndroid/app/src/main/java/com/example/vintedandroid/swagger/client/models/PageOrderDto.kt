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
 * @param totalPages 
 * @param totalElements 
 * @param pageable 
 * @param first 
 * @param last 
 * @param size 
 * @param content 
 * @param number 
 * @param sort 
 * @param numberOfElements 
 * @param empty 
 */
data class PageOrderDto (

    val totalPages: kotlin.Int? = null,
    val totalElements: kotlin.Long? = null,
    val pageable: String? = null, //PageableObject?
    val first: kotlin.Boolean? = null,
    val last: kotlin.Boolean? = null,
    val size: kotlin.Int? = null,
    val content: kotlin.Array<OrderDto>? = null,
    val number: kotlin.Int? = null,
    val sort: SortObject? = null,
    val numberOfElements: kotlin.Int? = null,
    val empty: kotlin.Boolean? = null
) {
    val results: List<OrderDto>
        get() = content.orEmpty().toList()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PageOrderDto

        if (totalPages != other.totalPages) return false
        if (totalElements != other.totalElements) return false
        if (pageable != other.pageable) return false
        if (first != other.first) return false
        if (last != other.last) return false
        if (size != other.size) return false
        if (content != null) {
            if (other.content == null) return false
            if (!content.contentEquals(other.content)) return false
        } else if (other.content != null) return false
        if (number != other.number) return false
        if (sort != other.sort) return false
        if (numberOfElements != other.numberOfElements) return false
        if (empty != other.empty) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalPages ?: 0
        result = 31 * result + (totalElements?.hashCode() ?: 0)
        result = 31 * result + (pageable?.hashCode() ?: 0)
        result = 31 * result + (first?.hashCode() ?: 0)
        result = 31 * result + (last?.hashCode() ?: 0)
        result = 31 * result + (size ?: 0)
        result = 31 * result + (content?.contentHashCode() ?: 0)
        result = 31 * result + (number ?: 0)
        result = 31 * result + (sort?.hashCode() ?: 0)
        result = 31 * result + (numberOfElements ?: 0)
        result = 31 * result + (empty?.hashCode() ?: 0)
        return result
    }
}