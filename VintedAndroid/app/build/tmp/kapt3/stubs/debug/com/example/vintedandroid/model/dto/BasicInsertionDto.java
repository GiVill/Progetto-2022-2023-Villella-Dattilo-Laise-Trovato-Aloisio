package com.example.vintedandroid.model.dto;

import java.lang.System;

@androidx.room.Entity(tableName = "basicInsertionDto")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eR\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015\u00a8\u0006\u001b"}, d2 = {"Lcom/example/vintedandroid/model/dto/BasicInsertionDto;", "", "id", "", "title", "", "price", "", "description", "condition", "creationDate", "endDate", "imagePath", "userId", "(JLjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getCondition", "()Ljava/lang/String;", "getCreationDate", "getDescription", "getEndDate", "getId", "()J", "getImagePath", "getPrice", "()I", "getTitle", "getUserId", "app_debug"})
public final class BasicInsertionDto {
    @androidx.room.PrimaryKey
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String title = null;
    @androidx.room.ColumnInfo
    private final int price = 0;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String condition = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String creationDate = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String endDate = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String imagePath = null;
    @androidx.room.ColumnInfo
    private final long userId = 0L;
    
    public BasicInsertionDto(long id, @org.jetbrains.annotations.NotNull
    java.lang.String title, int price, @org.jetbrains.annotations.NotNull
    java.lang.String description, @org.jetbrains.annotations.NotNull
    java.lang.String condition, @org.jetbrains.annotations.NotNull
    java.lang.String creationDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    java.lang.String imagePath, long userId) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final int getPrice() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCondition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCreationDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getImagePath() {
        return null;
    }
    
    public final long getUserId() {
        return 0L;
    }
}