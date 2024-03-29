package com.example.vintedandroid.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vintedandroid.swagger.client.models.UserDto
import java.util.UUID

@Entity(tableName = "userDatabaseDto")
data class UserDatabaseDto (

    @PrimaryKey val id: Long?,
    @ColumnInfo(name="nick_name")val nickName: kotlin.String,
    @ColumnInfo(name="first_name")val firstName: kotlin.String,
    @ColumnInfo(name="last_name")val lastName: kotlin.String? = null,
    @ColumnInfo(name="email")val email: kotlin.String? = null,
    @ColumnInfo(name="password")val password: kotlin.String? = null,
    @ColumnInfo(name="image_name")val imageName: kotlin.String? = null,
    @ColumnInfo(name="address_street")val addressStreet: kotlin.String? = null,
    @ColumnInfo(name="address_number")val addressNumber: kotlin.Int? = null,
    @ColumnInfo(name="address_city")val addressCity: kotlin.String? = null,
    @ColumnInfo(name="address_cap")val addressCap: kotlin.Int? = null,
    @ColumnInfo(name="address_state")val addressState: kotlin.String? = null,
    @ColumnInfo(name="address_region")val addressRegion: kotlin.String? = null,
    @ColumnInfo(name="access_token")val accessToken: kotlin.String? = null,
    @ColumnInfo(name="refresh_token")val refreshToken: kotlin.String? = null

)