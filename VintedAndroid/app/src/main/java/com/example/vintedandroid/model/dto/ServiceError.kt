package com.example.vintedandroid.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.Date

class ServiceError (
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    val timestamp : LocalDateTime, // controllare se è meglio questo o Date
    val url : String,
    val message : String
        ){

}