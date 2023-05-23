package com.example.vintedandroid.dto

import com.example.vintedandroid.dto.enumerated.PaymentMethod
import com.example.vintedandroid.dto.enumerated.Status

class PaymentDto(
    val id : Long,
    val paymentMethod: PaymentMethod,
    val status: Status,
    val userId : Long,
    val orderId : Long
) {
}