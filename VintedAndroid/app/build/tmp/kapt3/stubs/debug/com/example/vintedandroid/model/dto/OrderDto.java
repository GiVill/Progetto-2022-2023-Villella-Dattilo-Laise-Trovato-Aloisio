package com.example.vintedandroid.model.dto;

import java.lang.System;

@androidx.room.Entity(tableName = "orderDto")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f\u00a8\u0006\u0015"}, d2 = {"Lcom/example/vintedandroid/model/dto/OrderDto;", "", "id", "", "date", "", "paymentid", "number", "", "inserionId", "userId", "(JLjava/lang/String;JIJJ)V", "getDate", "()Ljava/lang/String;", "getId", "()J", "getInserionId", "getNumber", "()I", "getPaymentid", "getUserId", "app_debug"})
public final class OrderDto {
    @androidx.room.PrimaryKey
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String date = null;
    @androidx.room.ColumnInfo
    private final long paymentid = 0L;
    @androidx.room.ColumnInfo
    private final int number = 0;
    @androidx.room.ColumnInfo
    private final long inserionId = 0L;
    @androidx.room.ColumnInfo
    private final long userId = 0L;
    
    public OrderDto(long id, @org.jetbrains.annotations.NotNull
    java.lang.String date, long paymentid, int number, long inserionId, long userId) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDate() {
        return null;
    }
    
    public final long getPaymentid() {
        return 0L;
    }
    
    public final int getNumber() {
        return 0;
    }
    
    public final long getInserionId() {
        return 0L;
    }
    
    public final long getUserId() {
        return 0L;
    }
}