package com.example.vintedandroid.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "cartDto")
data class CartDto(

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name="title") val title:String,
    @ColumnInfo(name="price") val price: Float,
    @ColumnInfo(name="description") val description: String? = null,
    @ColumnInfo(name="condition") val condition: String? = null,
    //@ColumnInfo(name="creationDate") val creationDate: LocalDate? = null,
    @ColumnInfo(name="isPrivate") val isPrivate: Boolean? = null,
    //@ColumnInfo(name="endDate") val endDate: LocalDate? = null,
    @ColumnInfo(name="imageName") val imageName: String? = null,
    //@ColumnInfo(name="brand") val brand: BasicInsertionDto.Brand? = null,
    //@ColumnInfo(name="category") val category: BasicInsertionDto.Category? = null,
    @ColumnInfo(name="userId") val userId: Long
){}