package com.example.vintedandroid.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vintedandroid.dto.enumerated.Gender
import java.time.LocalDate

@Entity(tableName = "userDto")
class UserDto (
    @PrimaryKey val id : Long,
    @ColumnInfo val firstName : String,
    @ColumnInfo val lastName : String,
    @ColumnInfo val email : String,
    @ColumnInfo val birthDate: LocalDate,
    @ColumnInfo val gender : Gender
        ){
}