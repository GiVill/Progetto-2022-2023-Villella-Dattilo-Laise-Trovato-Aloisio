package com.example.vintedandroid.dto

import javax.net.ssl.SSLEngineResult.Status

class BuyingOfferDto(
    val id : Long,
    val price : Float,
    val status: Status,
    val insertionId : Long,
    val userId : Long
) {
}