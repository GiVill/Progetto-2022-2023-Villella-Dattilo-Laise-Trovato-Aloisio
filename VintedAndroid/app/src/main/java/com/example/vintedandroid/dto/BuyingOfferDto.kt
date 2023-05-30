package com.example.vintedandroid.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.net.ssl.SSLEngineResult.Status

@Entity(tableName = "buyingOfferDto")
class BuyingOfferDto(
    @PrimaryKey val id : Long,
    @ColumnInfo val price : Float,
    @ColumnInfo val status: Status,
    @ColumnInfo val insertionId : Long,
    @ColumnInfo val userId : Long
) {
}