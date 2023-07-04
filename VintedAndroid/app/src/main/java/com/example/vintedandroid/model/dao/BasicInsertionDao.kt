package com.example.vintedandroid.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.vintedandroid.model.dto.BasicInsertionDto
import com.example.vintedandroid.model.dto.OrderDto

@Dao
interface BasicInsertionDao {
    @Query("select * from basicInsertionDto")
    fun getAll(): List<BasicInsertionDto>

    @Query("select * from basicInsertionDto where id = :basicId")
    fun getById(basicId : Long) : BasicInsertionDto
}