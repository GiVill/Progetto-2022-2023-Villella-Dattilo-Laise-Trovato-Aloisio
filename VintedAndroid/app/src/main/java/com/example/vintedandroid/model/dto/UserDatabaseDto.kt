package com.example.vintedandroid.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "userDatabaseDto")
data class UserDatabaseDto (

    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name="nick_name")val nickName: kotlin.String,
    @ColumnInfo(name="first_name")val firstName: kotlin.String,
    @ColumnInfo(name="last_name")val lastName: kotlin.String?,
    @ColumnInfo(name="email")val email: kotlin.String? = null,
    @ColumnInfo(name="password")val password: kotlin.String? = null,
    @ColumnInfo(name="birthDate")val birthDate: kotlin.String? = null,
    @ColumnInfo(name="gender")val gender: Gender? = null,
    @ColumnInfo(name="address_street")val addressStreet: kotlin.String? = null,
    @ColumnInfo(name="address_number")val addressNumber: kotlin.Int? = null,
    @ColumnInfo(name="address_city")val addressCity: kotlin.String? = null,
    @ColumnInfo(name="address_cap")val addressCap: kotlin.Int? = null,
    @ColumnInfo(name="address_state")val addressState: kotlin.String? = null,
    @ColumnInfo(name="address_region")val addressRegion: kotlin.String? = null,
    @ColumnInfo(name="jwt")val jwt: kotlin.String? = null
) {
    /**
     *
     * Values: MALE,FEMALE,OTHER
     */
    enum class Gender(val value: kotlin.String){
        MALE("MALE"),
        FEMALE("FEMALE"),
        OTHER("OTHER");
    }
}