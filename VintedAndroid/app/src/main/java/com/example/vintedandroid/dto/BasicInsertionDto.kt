package com.example.vintedandroid.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "basicInsertionDto")
class BasicInsertionDto (
    @PrimaryKey val id : Long,
    @ColumnInfo val title : String,
    @ColumnInfo val price : Int,
    @ColumnInfo val description : String,
    @ColumnInfo val condition : String,
    @ColumnInfo val creationDate : LocalDate,
    @ColumnInfo val endDate: LocalDate,
    @ColumnInfo val imagePath : String,
    @ColumnInfo val userId : Long
        ){
}