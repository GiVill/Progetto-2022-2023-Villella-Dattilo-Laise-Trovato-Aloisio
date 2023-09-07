package com.example.vintedandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vintedandroid.swagger.client.apis.InsertionApi
import com.example.vintedandroid.swagger.client.apis.OrderApi
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.swagger.client.models.PageOrderDto
import kotlinx.coroutines.flow.Flow

class OrderViewModel() : ViewModel() {

    fun getOrders(page: Int): PageOrderDto {
        return OrderApi().getUserOrders(page)
    }

    fun getInsertionById(id: Long): BasicInsertionDto {
        return InsertionApi().getInsertionById(id)
    }

}