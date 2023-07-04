package com.example.vintedandroid.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vintedandroid.model.dto.enumerated.Gender
import java.time.LocalDate

@Entity(tableName = "userDto")
class UserDto (
    @PrimaryKey val id : Long,
    @ColumnInfo val firstName : String,
    @ColumnInfo val lastName : String,
    @ColumnInfo val email : String,
    @ColumnInfo val birthDate: String,
    @ColumnInfo val gender : Gender
        ){
}