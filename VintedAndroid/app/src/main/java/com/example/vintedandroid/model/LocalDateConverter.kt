package com.example.vintedandroid.model

import androidx.room.TypeConverter
import java.time.LocalDate

//TODO Questa classe non dovrebbe essere necessaria, forse. Servirebbe per convertire i tipi LocalDate inviati dal backend

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.toString()
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }
}