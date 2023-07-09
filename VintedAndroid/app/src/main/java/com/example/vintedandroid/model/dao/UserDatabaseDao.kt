package com.example.vintedandroid.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vintedandroid.client.models.UserDatabaseDto

@Dao
interface UserDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserDatabaseDto)

    @Update
    suspend fun update(userDatabaseDto: UserDatabaseDto)

    @Query("select * from userDatabaseDto")
    fun getAll(): List<UserDatabaseDto>

    @Query("select * from userDatabaseDto where id = :userId")
    fun getUserById(userId : Long ): UserDatabaseDto
}