package com.example.vintedandroid.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vintedandroid.swagger.client.models.BasicInsertionDto
import com.example.vintedandroid.model.dto.CartDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(insertion: CartDto)

    @Update
    suspend fun update(insertion: CartDto)

    @Delete
    suspend fun delete(insertion: CartDto)

    @Query("DELETE FROM cartDto")
    suspend fun deleteAll();

    @Query("select * from cartDto")
    fun getAll(): Flow<MutableList<CartDto>>
}