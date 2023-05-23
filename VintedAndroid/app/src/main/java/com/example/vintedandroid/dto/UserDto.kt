package com.example.vintedandroid.dto

import com.example.vintedandroid.dto.enumerated.Gender
import java.time.LocalDate

class UserDto (
    val id : Long,
    val firstName : String,
    val lastName : String,
    val email : String,
    val birthDate: LocalDate,
    val gender : Gender
        ){
}