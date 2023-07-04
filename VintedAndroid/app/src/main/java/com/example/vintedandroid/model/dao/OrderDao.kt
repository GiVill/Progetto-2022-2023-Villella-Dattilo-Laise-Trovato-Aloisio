package com.example.vintedandroid.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.vintedandroid.model.dto.OrderDto

@Dao
interface OrderDao {
    @Query("select * from orderDto")
    fun getAll(): List<OrderDto>

    @Query("select * from orderDto where id = :orderId")
    fun getById(orderId : Long) : OrderDto
}