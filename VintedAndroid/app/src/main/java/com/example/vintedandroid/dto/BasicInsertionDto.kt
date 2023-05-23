package com.example.vintedandroid.dto

import java.time.LocalDate

class BasicInsertionDto (
    val id : Long,
    val title : String,
    val price : Int,
    val description : String,
    val condition : String,
    val creationDate : LocalDate,
    val endDate: LocalDate,
    val imagePath : String,
    val userId : Long
        ){
}