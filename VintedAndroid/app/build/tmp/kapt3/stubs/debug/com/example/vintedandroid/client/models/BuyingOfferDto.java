package com.example.vintedandroid.client.models;

import java.lang.System;

/**
 * @param id 
 * @param price 
 * @param status 
 * @param insertionId 
 * @param userId
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001 B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J=\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006!"}, d2 = {"Lcom/example/vintedandroid/client/models/BuyingOfferDto;", "", "id", "", "price", "", "status", "Lcom/example/vintedandroid/client/models/BuyingOfferDto$Status;", "insertionId", "userId", "(JFLcom/example/vintedandroid/client/models/BuyingOfferDto$Status;JJ)V", "getId", "()J", "getInsertionId", "getPrice", "()F", "getStatus", "()Lcom/example/vintedandroid/client/models/BuyingOfferDto$Status;", "getUserId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Status", "app_debug"})
public final class BuyingOfferDto {
    private final long id = 0L;
    private final float price = 0.0F;
    @org.jetbrains.annotations.Nullable
    private final com.example.vintedandroid.client.models.BuyingOfferDto.Status status = null;
    private final long insertionId = 0L;
    private final long userId = 0L;
    
    /**
     * @param id 
     * @param price 
     * @param status 
     * @param insertionId 
     * @param userId
     */
    @org.jetbrains.annotations.NotNull
    public final com.example.vintedandroid.client.models.BuyingOfferDto copy(long id, float price, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.BuyingOfferDto.Status status, long insertionId, long userId) {
        return null;
    }
    
    /**
     * @param id 
     * @param price 
     * @param status 
     * @param insertionId 
     * @param userId
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * @param id 
     * @param price 
     * @param status 
     * @param insertionId 
     * @param userId
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * @param id 
     * @param price 
     * @param status 
     * @param insertionId 
     * @param userId
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public BuyingOfferDto(long id, float price, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.BuyingOfferDto.Status status, long insertionId, long userId) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    public final float getPrice() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.BuyingOfferDto.Status component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.BuyingOfferDto.Status getStatus() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long getInsertionId() {
        return 0L;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getUserId() {
        return 0L;
    }
    
    /**
     * Values: PENDING,APPROVED
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/example/vintedandroid/client/models/BuyingOfferDto$Status;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "PENDING", "APPROVED", "app_debug"})
    public static enum Status {
        /*public static final*/ PENDING /* = new PENDING(null) */,
        /*public static final*/ APPROVED /* = new APPROVED(null) */;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String value = null;
        
        Status(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getValue() {
            return null;
        }
    }
}