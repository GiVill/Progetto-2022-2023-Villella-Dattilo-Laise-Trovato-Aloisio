package com.example.vintedandroid.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.vintedandroid.dto.PaymentDto

@Dao
public interface PaymentDao {
    @Query("select * from paymentDto")
    fun getAll(): List<PaymentDto>

    @Query("select * from paymentDto where id = :paymentId")
    fun getById(paymentId : Long) : PaymentDto
}