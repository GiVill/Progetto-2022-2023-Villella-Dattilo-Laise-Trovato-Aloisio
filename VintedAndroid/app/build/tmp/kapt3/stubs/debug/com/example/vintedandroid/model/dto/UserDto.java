package com.example.vintedandroid.model.dto;

import java.lang.System;

@androidx.room.Entity(tableName = "userDto")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r\u00a8\u0006\u0015"}, d2 = {"Lcom/example/vintedandroid/model/dto/UserDto;", "", "id", "", "firstName", "", "lastName", "email", "birthDate", "gender", "Lcom/example/vintedandroid/model/dto/enumerated/Gender;", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/vintedandroid/model/dto/enumerated/Gender;)V", "getBirthDate", "()Ljava/lang/String;", "getEmail", "getFirstName", "getGender", "()Lcom/example/vintedandroid/model/dto/enumerated/Gender;", "getId", "()J", "getLastName", "app_debug"})
public final class UserDto {
    @androidx.room.PrimaryKey
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String firstName = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String lastName = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String email = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String birthDate = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final com.example.vintedandroid.model.dto.enumerated.Gender gender = null;
    
    public UserDto(long id, @org.jetbrains.annotations.NotNull
    java.lang.String firstName, @org.jetbrains.annotations.NotNull
    java.lang.String lastName, @org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    java.lang.String birthDate, @org.jetbrains.annotations.NotNull
    com.example.vintedandroid.model.dto.enumerated.Gender gender) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFirstName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLastName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBirthDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.vintedandroid.model.dto.enumerated.Gender getGender() {
        return null;
    }
}