package com.example.vintedandroid.model.dto;

import java.lang.System;

@androidx.room.Entity(tableName = "buyingOfferDto")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006\u0013"}, d2 = {"Lcom/example/vintedandroid/model/dto/BuyingOfferDto;", "", "id", "", "price", "", "status", "Ljavax/net/ssl/SSLEngineResult$Status;", "insertionId", "userId", "(JFLjavax/net/ssl/SSLEngineResult$Status;JJ)V", "getId", "()J", "getInsertionId", "getPrice", "()F", "getStatus", "()Ljavax/net/ssl/SSLEngineResult$Status;", "getUserId", "app_debug"})
public final class BuyingOfferDto {
    @androidx.room.PrimaryKey
    private final long id = 0L;
    @androidx.room.ColumnInfo
    private final float price = 0.0F;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final javax.net.ssl.SSLEngineResult.Status status = null;
    @androidx.room.ColumnInfo
    private final long insertionId = 0L;
    @androidx.room.ColumnInfo
    private final long userId = 0L;
    
    public BuyingOfferDto(long id, float price, @org.jetbrains.annotations.NotNull
    javax.net.ssl.SSLEngineResult.Status status, long insertionId, long userId) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final float getPrice() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final javax.net.ssl.SSLEngineResult.Status getStatus() {
        return null;
    }
    
    public final long getInsertionId() {
        return 0L;
    }
    
    public final long getUserId() {
        return 0L;
    }
}