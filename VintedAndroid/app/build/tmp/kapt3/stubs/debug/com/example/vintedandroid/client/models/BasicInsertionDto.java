package com.example.vintedandroid.client.models;

import java.lang.System;

/**
 * @param id 
 * @param title 
 * @param price 
 * @param description 
 * @param condition 
 * @param creationDate 
 * @param endDate 
 * @param imagePath 
 * @param userId
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003Jm\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\r\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020*H\u00d6\u0001J\t\u0010+\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016\u00a8\u0006,"}, d2 = {"Lcom/example/vintedandroid/client/models/BasicInsertionDto;", "", "id", "", "title", "", "price", "", "description", "condition", "creationDate", "endDate", "imagePath", "userId", "(JLjava/lang/String;FLjava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;J)V", "getCondition", "()Ljava/lang/String;", "getCreationDate", "()Ljava/lang/Object;", "getDescription", "getEndDate", "getId", "()J", "getImagePath", "getPrice", "()F", "getTitle", "getUserId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class BasicInsertionDto {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = null;
    private final float price = 0.0F;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String description = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String condition = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object creationDate = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object endDate = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String imagePath = null;
    private final long userId = 0L;
    
    /**
     * @param id 
     * @param title 
     * @param price 
     * @param description 
     * @param condition 
     * @param creationDate 
     * @param endDate 
     * @param imagePath 
     * @param userId
     */
    @org.jetbrains.annotations.NotNull
    public final com.example.vintedandroid.client.models.BasicInsertionDto copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String title, float price, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String condition, @org.jetbrains.annotations.Nullable
    java.lang.Object creationDate, @org.jetbrains.annotations.Nullable
    java.lang.Object endDate, @org.jetbrains.annotations.Nullable
    java.lang.String imagePath, long userId) {
        return null;
    }
    
    /**
     * @param id 
     * @param title 
     * @param price 
     * @param description 
     * @param condition 
     * @param creationDate 
     * @param endDate 
     * @param imagePath 
     * @param userId
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * @param id 
     * @param title 
     * @param price 
     * @param description 
     * @param condition 
     * @param creationDate 
     * @param endDate 
     * @param imagePath 
     * @param userId
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * @param id 
     * @param title 
     * @param price 
     * @param description 
     * @param condition 
     * @param creationDate 
     * @param endDate 
     * @param imagePath 
     * @param userId
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public BasicInsertionDto(long id, @org.jetbrains.annotations.NotNull
    java.lang.String title, float price, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String condition, @org.jetbrains.annotations.Nullable
    java.lang.Object creationDate, @org.jetbrains.annotations.Nullable
    java.lang.Object endDate, @org.jetbrains.annotations.Nullable
    java.lang.String imagePath, long userId) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final float getPrice() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCondition() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCreationDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImagePath() {
        return null;
    }
    
    public final long component9() {
        return 0L;
    }
    
    public final long getUserId() {
        return 0L;
    }
}