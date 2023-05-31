package com.example.vintedandroid.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vintedandroid.dto.enumerated.PaymentMethod
import com.example.vintedandroid.dto.enumerated.Status

@Entity(tableName = "paymentDto")
class PaymentDto(
    @PrimaryKey val id : Long?,
    @ColumnInfo val paymentMethod: PaymentMethod?,
    @ColumnInfo val status: Status?,
    @ColumnInfo val userId : Long?,
    @ColumnInfo val orderId : Long?
) {
    constructor() : this(null, null, null, null, null)
}