package com.example.vintedandroid.model.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PaymentDao {
    @Query("select * from paymentDto")
    fun getAll(): List<PaymentDao>

    @Query("select * from paymentDto where id = :paymentId")
    fun getById(paymentId : Long) : PaymentDao
}