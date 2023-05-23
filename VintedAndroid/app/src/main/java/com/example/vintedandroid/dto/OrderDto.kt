package com.example.vintedandroid.dto

import java.time.LocalDate


class OrderDto (
    val id : Long,
    val date: LocalDate,
    val paymentid : Long,
    val number : Int,
    val inserionId : Long,
    val userId : Long
        ){
}