package com.example.vintedandroid.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.vintedandroid.dto.BuyingOfferDto
import com.example.vintedandroid.dto.OrderDto

@Dao
interface BuyingOfferDao {
    @Query("select * from buyingOfferDto")
    fun getAll(): List<BuyingOfferDto>

    @Query("select * from buyingOfferDto where id = :buyingId")
    fun getById(buyingId : Long) : BuyingOfferDto
}