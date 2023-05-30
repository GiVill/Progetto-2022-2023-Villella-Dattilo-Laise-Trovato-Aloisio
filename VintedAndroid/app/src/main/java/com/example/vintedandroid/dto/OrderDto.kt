package com.example.vintedandroid.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "orderDto")
class OrderDto (
    @PrimaryKey val id : Long,
    @ColumnInfo val date: LocalDate,
    @ColumnInfo val paymentid : Long,
    @ColumnInfo val number : Int,
    @ColumnInfo val inserionId : Long,
    @ColumnInfo val userId : Long
        ){
}