package com.example.vintedandroid.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.vintedandroid.dto.UserDto

@Dao
interface UserDao {
    @Query("select * from userDto")
    fun getAll(): List<UserDto>

    @Query("select * from userdto where id = :userId")
    fun getById(userId : Long ): UserDto
}