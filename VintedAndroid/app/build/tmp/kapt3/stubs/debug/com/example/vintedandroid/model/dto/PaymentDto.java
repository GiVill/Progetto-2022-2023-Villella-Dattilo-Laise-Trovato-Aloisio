package com.example.vintedandroid.model.dto;

import java.lang.System;

@androidx.room.Entity(tableName = "paymentDto")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B7\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u000bR\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001a\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0014\u0010\r\u00a8\u0006\u0015"}, d2 = {"Lcom/example/vintedandroid/model/dto/PaymentDto;", "", "()V", "id", "", "paymentMethod", "Lcom/example/vintedandroid/model/dto/enumerated/PaymentMethod;", "status", "Lcom/example/vintedandroid/model/dto/enumerated/Status;", "userId", "orderId", "(Ljava/lang/Long;Lcom/example/vintedandroid/model/dto/enumerated/PaymentMethod;Lcom/example/vintedandroid/model/dto/enumerated/Status;Ljava/lang/Long;Ljava/lang/Long;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getOrderId", "getPaymentMethod", "()Lcom/example/vintedandroid/model/dto/enumerated/PaymentMethod;", "getStatus", "()Lcom/example/vintedandroid/model/dto/enumerated/Status;", "getUserId", "app_debug"})
public final class PaymentDto {
    @org.jetbrains.annotations.Nullable
    @androidx.room.PrimaryKey
    private final java.lang.Long id = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo
    private final com.example.vintedandroid.model.dto.enumerated.PaymentMethod paymentMethod = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo
    private final com.example.vintedandroid.model.dto.enumerated.Status status = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo
    private final java.lang.Long userId = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo
    private final java.lang.Long orderId = null;
    
    public PaymentDto(@org.jetbrains.annotations.Nullable
    java.lang.Long id, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.model.dto.enumerated.PaymentMethod paymentMethod, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.model.dto.enumerated.Status status, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.Long orderId) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.model.dto.enumerated.PaymentMethod getPaymentMethod() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.model.dto.enumerated.Status getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getOrderId() {
        return null;
    }
    
    public PaymentDto() {
        super();
    }
}